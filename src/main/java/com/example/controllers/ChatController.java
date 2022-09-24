package com.example.controllers;

import com.example.models.Chat;
import com.example.models.Member;
import com.example.models.Message;
import com.example.security.MemberDetails;
import com.example.services.ChatService;
import com.example.services.MemberService;
import com.example.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/chats")
public class ChatController {

    private final ChatService chatService;
    private final MessageService messageService;
    private final MemberService memberService;

    @Autowired
    public ChatController(ChatService chatService, MessageService messageService, MemberService memberService) {
        this.chatService = chatService;
        this.messageService = messageService;
        this.memberService = memberService;

    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("chats", chatService.findAll());
        return "chats/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Member member = getCurrentMemberFromContext();

        model.addAttribute("member", member);
        model.addAttribute("chat", chatService.findOne(id));
//        model.addAttribute("messages", messageService.findMessageByChatId(id));
        return "chats/show";
    }

    @GetMapping("/{id}/chat")
    public String chat(@PathVariable("id") int id, Model model) {
        model.addAttribute("chat", chatService.findOne(id));
        return "chats/chat";
    }

    @PostMapping("/{id}/chat")
    public String addToChat(@PathVariable("id") int id, Model model) {
        Member member = getCurrentMemberFromContext();
        Chat chat = chatService.findOne(id);
        chat.getMembers().add(member);

        model.addAttribute("chat", chat);
        chatService.update(id, chat);

        return "chats/chat";
    }

    @PostMapping("/{id}")
    public String leaveChat(@PathVariable("id") int id) {
        Member member = getCurrentMemberFromContext();
        Chat chat = chatService.findOne(id);
        chat.getMembers().remove(member);

        chatService.update(id, chat);
        return "redirect:" + id;
    }


    @GetMapping("/send/{id}")
    public String send(@PathVariable("id") int id, Model model) {
        model.addAttribute("chat", chatService.findOne(id));
        return "chats/chat";
    }

    @PostMapping("/send/{id}")
    public String send(@PathVariable("id") int id, Model model, @RequestParam("text") String text) {
        Chat chat = chatService.findOne(id);
        Member member = getCurrentMemberFromContext();
        Message message = new Message(member.getId(), chat.getId(), text, member.getUsername());
        chat.getMessages().add(message);

        messageService.save(message);
        chatService.update(id, chat);

        model.addAttribute("chat", chat);
        return "chats/chat";
    }


    private Member getCurrentMemberFromContext(){
        MemberDetails memberDetails = (MemberDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return memberService.findOne(memberDetails.getPerson().getId());
    }
}

