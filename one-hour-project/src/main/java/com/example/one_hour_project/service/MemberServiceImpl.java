package com.example.one_hour_project.service;

import com.example.one_hour_project.repository.MemberRepository;
import com.example.one_hour_project.repository.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public String join(String id, String name, String phoneNumber) {
        Member member = Member.builder()
                        .id(id)
                        .name(name)
                        .phoneNumber(phoneNumber)
                        .build();

        memberRepository.save(member);
        return "Success";
    }
}
