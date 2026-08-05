package com.example.cloudassignment.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "memberentitys")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String age;
    private String mbti;

    public MemberEntity(String name, String age, String mbti) {
        this.name = name;
        this.age = age;
        this.mbti = mbti;
    }
}
