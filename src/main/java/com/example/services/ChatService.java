package com.example.services;



import com.example.models.Chat;
import com.example.repositories.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ChatService {
    private final ChatRepository chatRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public List<Chat> findAll() {
        return chatRepository.findAll();
    }

    public Chat findOne(int id) {
        Optional<Chat> foundPerson = chatRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Chat chat) {
        chatRepository.save(chat);
    }

    @Transactional
    public void update(int id, Chat updatedChat) {
        updatedChat.setId(id);
        chatRepository.save(updatedChat);
    }

    @Transactional
    public void delete(int id) {
        chatRepository.deleteById(id);
    }

    public Optional<Chat> getChatByName(String name){
        return chatRepository.getChatByName(name);
    }
}
