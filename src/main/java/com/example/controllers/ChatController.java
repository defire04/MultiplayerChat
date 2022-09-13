package com.example.controllers;

import com.example.models.Chat;
import com.example.models.Member;
import com.example.services.ChatService;
import com.example.services.MemberService;
import com.example.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
        return "chats/show";
    }

    @GetMapping("/{id}/chat")
    public String chat(@ModelAttribute("member") Member member, Model model, @PathVariable("id") int id) {
        System.out.println("------------------------");
        Chat chat = chatService.findOne(id);

        chat.getMembers().add(member);

        chatService.update(id, chat);

        return "chats/chat";
    }

}
