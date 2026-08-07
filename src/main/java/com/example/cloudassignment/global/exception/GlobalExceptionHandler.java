package com.example.cloudassignment.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

//    MemberNotFoundException → 404 Not Found (존재하지 않는 회원 조회)
    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMemberNotFoundException(MemberNotFoundException exception) {
        log.error("멤버 조회 중 오류가 발생했습니다", exception);

        ErrorResponse response = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "MEMBER_NOT_FOUND",
                exception.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(ProfileImageNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProfileImageNotFoundException(ProfileImageNotFoundException exception) {
        log.error("프로필 이미지 조회 중 오류가 발생했습니다", exception);

        ErrorResponse response = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "PROFILE_IMAGE_NOT_FOUND",
                exception.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

//    MethodArgumentNotValidException → 400 Bad Request (@Valid 실패)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.error("요청값 검증 중 오류가 발생했습니다.", exception);

        String message = exception
                .getBindingResult()
                .getFieldErrors()
                .get(0)
                .getDefaultMessage();

        ErrorResponse response = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "INVALID_REQUEST",
                message
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
//    HttpMessageNotReadableException → 400 Bad Request
//    (존재하지 않는 Enum 값/이 경우는 @Valid까지 도달하기 전에 JSON을 Mbti Enum으로 변환하지 못한 것의 응답)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        log.error("JSON 요청을 읽는 중 오류가 발생했습니다", exception);

        ErrorResponse response = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "INVALID_REQUEST",
                "요청 본문의 형식 또는 값이 올바르지 않습니다"
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException exception) {
        log.error("잘못된 요청값이 전달되었습니다", exception);

        ErrorResponse response = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "INVALID_REQUEST",
                exception.getMessage()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


//    Exception → 500 Internal Server Error (예상하지 못한 오류)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        log.error("예상하지 못한 서버 오류가 발생했습니다", exception);

        ErrorResponse response = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "INTERNAL_SERVER_ERROR",
                "서버 내부 오류가 발생했습니다"
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

}
