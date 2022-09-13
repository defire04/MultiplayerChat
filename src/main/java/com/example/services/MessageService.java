package com.example.services;

import com.example.models.Message;
import com.example.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository chatRepository) {
        this.messageRepository = chatRepository;
    }

    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    public Message findOne(int id) {
        Optional<Message> foundMessage = messageRepository.findById(id);
        return foundMessage.orElse(null);
    }

    @Transactional
    public void save(Message msg) {
        messageRepository.save(msg);
    }

    @Transactional
    public void update(int id, Message updatedMsg) {
        updatedMsg.setId(id);
        messageRepository.save(updatedMsg);
    }

    @Transactional
    public void delete(int id) {
        messageRepository.deleteById(id);
    }


    @Transactional
    public List<Message> findMessageByChatId(int chatId){
        return messageRepository.findMessageByChatId(chatId);
    }
}
