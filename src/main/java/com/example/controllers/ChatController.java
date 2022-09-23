package com.example.controllers;

import com.example.models.Chat;
import com.example.models.Member;
import com.example.models.Message;
import com.example.security.MemberDetails;
//import com.example.services.ChatMemberService;
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
        model.addAttribute("chat", chatService.findOne(id));
        model.addAttribute("messages", messageService.findMessageByChatId(id));

        MemberDetails memberDetails = (MemberDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Member member = memberService.findOne(memberDetails.getPerson().getId());

        model.addAttribute("member", member);

        return "chats/show";
    }


    @GetMapping("/{id}/chat")
    public String chat(@PathVariable("id") int id, Model model) {
        model.addAttribute("chat", chatService.findOne(id));
        return "chats/chat";
    }

    @PostMapping("/{id}/chat")
    public String addToChat(@PathVariable("id") int id, Model model) {


        MemberDetails memberDetails = (MemberDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Member member = memberDetails.getPerson();

        Chat chat = chatService.findOne(id);
        chat.getMembers().add(member);

        model.addAttribute("chat", chat);
        chatService.update(id, chat);

////        System.out.println(member);
//        System.out.println(member.getId());
//        System.out.println(member.getUsername());


        return "chats/chat";
    }


    @PostMapping("/{id}")
    public String leaveChat(@PathVariable("id") int id) {
        MemberDetails memberDetails = (MemberDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Member member = memberService.findOne(memberDetails.getPerson().getId());

        Chat chat = chatService.findOne(id);
        chat.getMembers().remove(member);


        chatService.update(id, chat);


        return "redirect:" + id;
    }

    @PostMapping("/send/{id}")
    public String send(@PathVariable("id") int id, Model model, @RequestParam("text") String text) {
        Chat chat = chatService.findOne(id);
        Member member = memberService.findOne(((MemberDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getPerson().getId());

        Message message = new Message(member.getId(), chat.getId(), text);
        chat.getMessages().add(message);


        messageService.save(message);
        chatService.update(id, chat);

        model.addAttribute("chat", chat);



        return "chats/chat";
    }

}
