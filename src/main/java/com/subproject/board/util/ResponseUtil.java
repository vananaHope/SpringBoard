package com.subproject.board.util;

import com.subproject.board.common.exception.ErrorCode;
import com.subproject.board.dto.ErrorResponse;

public class ResponseUtil {

    public static ErrorResponse error(String message){
        return new ErrorResponse("error",message);
    }

    public static ErrorResponse error(Exception e){
        return new ErrorResponse("error",e);
    }

    public static ErrorResponse error(ErrorCode errorCode){
        return new ErrorResponse("error",errorCode.getMessage());
    }
}
