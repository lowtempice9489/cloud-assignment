package com.example.cloudassignment.global.exception;

public class ProfileImageNotFoundException extends RuntimeException {

    public ProfileImageNotFoundException(Long memberId) {
        super("프로필 이미지가 존재하지 않습니다 memberId=" + memberId);
    }
}
