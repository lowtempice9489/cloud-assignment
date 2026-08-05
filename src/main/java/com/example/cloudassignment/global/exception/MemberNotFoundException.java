package com.example.cloudassignment.global.exception;

public class MemberNotFoundException extends RuntimeException {

    public MemberNotFoundException(Long memberId) {
        super("존재하지 않는 멤버입니다. member=" + memberId);
    }

}
