package com.example.repositories;

import com.example.models.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository  extends JpaRepository<Chat, Integer> {

}
