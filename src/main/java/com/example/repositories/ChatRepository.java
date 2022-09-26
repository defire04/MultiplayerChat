package com.example.repositories;

import com.example.models.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRepository  extends JpaRepository<Chat, Integer> {
    public Optional<Chat> getChatByName(String name);
}
