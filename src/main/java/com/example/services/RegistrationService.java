package com.example.services;

import com.example.models.Member;
import com.example.repositories.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(MemberRepository peopleRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = peopleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(Member person) {
        person.setPassword(passwordEncoder.encode(person.getPassword())); // Сохраняем в бд шифрованный пароль
        person.setRole("ROLE_USER");
        memberRepository.save(person);
    }
}
