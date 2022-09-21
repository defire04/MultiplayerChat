package com.example.repositories;

import com.example.models.ChatMemberInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMemberInfoRepository  extends JpaRepository<ChatMemberInfo, Integer> {
}
