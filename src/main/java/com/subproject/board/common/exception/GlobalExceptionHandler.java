package com.subproject.board.common.exception;


import com.subproject.board.util.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /* 커스텀 예외 처리 */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handleCustomException(CustomException e){
        return getErrorResponse(e.getErrorCode());
    }

    public ResponseEntity<?> getErrorResponse(ErrorCode errorCode){
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ResponseUtil.error(errorCode));
    }

}
