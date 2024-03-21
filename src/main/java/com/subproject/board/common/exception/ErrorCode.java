package com.subproject.board.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    /*일반 에러*/


    /*인증 에러*/
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST,"해당 사용자를 찾을 수 없습니다."),
    DUPLICATE_USERID(HttpStatus.BAD_REQUEST,"이미 사용 중인 아이디입니다.");

    private final HttpStatus httpStatus;
    private final String message;

}
