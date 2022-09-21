package com.example.controllers;

import com.example.models.Chat;
import com.example.models.Member;
import com.example.security.MemberDetails;
import com.example.services.ChatMemberService;
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

    private final ChatMemberService chatMemberService;

    @Autowired
    public ChatController(ChatService chatService, MessageService messageService, MemberService memberService, ChatMemberService chatMemberService) {
        this.chatService = chatService;
        this.messageService = messageService;
        this.memberService = memberService;
        this.chatMemberService = chatMemberService;
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
        return "chats/show";
    }

    @GetMapping("/{id}/chat")
    public String chat(@PathVariable("id") int id, Model model) {
        System.out.println("------------------------");

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
    public String leaveChat(@PathVariable("id") int id){
        MemberDetails memberDetails = (MemberDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Member member = memberService.findOne(memberDetails.getPerson().getId());

        Chat chat = chatService.findOne(id);
        chat.getMembers().remove(member);


        chatService.update(id, chat);


        return "redirect:" + id;
    }

}
