package com.example.util;

import com.example.models.Chat;
import com.example.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ChatValidator implements Validator {

    private final ChatService chatService;

    @Autowired
    public ChatValidator(ChatService chatService) {
        this.chatService = chatService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Chat.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Chat chat = (Chat) target;

        if(chatService.getChatByName(chat.getName()).isPresent()){
            Chat returnedChat = chatService.getChatByName(chat.getName()).get();
            if(returnedChat.getId() != chat.getId()){
                errors.rejectValue("name", "", "A chat with the same name already exists");
            }
        }

    }
}
