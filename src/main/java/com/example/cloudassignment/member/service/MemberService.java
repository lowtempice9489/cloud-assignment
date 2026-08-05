package com.example.cloudassignment.member.service;

import com.example.cloudassignment.global.exception.MemberNotFoundException;
import com.example.cloudassignment.member.dto.MemberCreateRequest;
import com.example.cloudassignment.member.dto.MemberResponse;
import com.example.cloudassignment.member.entity.Member;
import com.example.cloudassignment.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponse create(MemberCreateRequest request) {

        Member member = new Member(
                request.getName(),
                request.getAge(),
                request.getMbti()
        );
        Member createdMember = memberRepository.save(member);
        return MemberResponse.from(createdMember);
    }


    @Transactional(readOnly = true)
    public MemberResponse getMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new MemberNotFoundException(memberId)
        );
        return MemberResponse.from(member);

    }
}
