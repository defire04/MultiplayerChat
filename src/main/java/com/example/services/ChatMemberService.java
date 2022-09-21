package com.example.services;

import com.example.repositories.ChatMemberInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ChatMemberService {

    private final ChatMemberInfoRepository chatMemberInfoRepository;


    @Autowired
    public ChatMemberService(ChatMemberInfoRepository chatMemberInfoRepository) {
        this.chatMemberInfoRepository = chatMemberInfoRepository;
    }

    public void delete(int memberId){
        chatMemberInfoRepository.deleteById(memberId);
    }
}
