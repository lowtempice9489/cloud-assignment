package com.example.cloudassignment.member.dto;

import com.example.cloudassignment.member.entity.Member;
import com.example.cloudassignment.member.type.Mbti;
import lombok.Getter;

@Getter
public class MemberResponse {

    private final Long id;
    private final String name;
    private final Integer age;
    private final Mbti mbti;

    public MemberResponse(Long id, String name, Integer age, Mbti mbti) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.mbti = mbti;
    }

    public static MemberResponse from(Member member) {
        return new MemberResponse(
                member.getId(),
                member.getName(),
                member.getAge(),
                member.getMbti()
        );
    }
}
