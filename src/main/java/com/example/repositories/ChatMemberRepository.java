package com.example.repositories;


import com.example.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMemberRepository extends JpaRepository<Member, Integer> {
}
