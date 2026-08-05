package com.example.cloudassignment.member.dto;

import com.example.cloudassignment.member.type.Mbti;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class MemberCreateRequest {

    @NotBlank(message = "이름은 필수입니다")
    private String name;

    @NotNull(message = "나이는 필수입니다")
    @Positive(message = "나이는 양수여야 합니다")
    private Integer age;

    @NotNull(message = "MBTI는 필수 입니다")
    private Mbti mbti;
}
