package com.example.services;

import com.example.models.Chat;
import com.example.models.Member;
import com.example.repositories.ChatRepository;
import com.example.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member findOne(int id) {
        Optional<Member> foundPerson = memberRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Member member) {
        memberRepository.save(member);
    }

    @Transactional
    public void update(int id, Member updatedMember) {
        updatedMember.setId(id);
        memberRepository.save(updatedMember);
    }

    @Transactional
    public void delete(int id) {
        memberRepository.deleteById(id);
    }
}
