package com.subproject.board.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    /*일반 에러*/
    FORBIDDEN_REQUEST(HttpStatus.FORBIDDEN,"해당 요청에 대한 권한이 없습니다."),

    /*인증 에러*/
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"인증되지 않았습니다. 다시 시도해주세요."),
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST,"해당 사용자를 찾을 수 없습니다."),
    DUPLICATE_USERID(HttpStatus.BAD_REQUEST,"이미 사용 중인 아이디입니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST,"잘못된 비밀번호입니다.");

    private final HttpStatus httpStatus;
    private final String message;

}
