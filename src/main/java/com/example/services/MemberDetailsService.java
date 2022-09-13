package com.example.services;

import com.example.models.Member;
import com.example.repositories.MemberRepository;

import com.example.security.MemberDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository peopleRepository;

    public MemberDetailsService(MemberRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> person = peopleRepository.findByUsername(username);

        if(person.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }

        return new MemberDetails(person.get());
    }
}
