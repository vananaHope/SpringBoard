package com.subproject.board.dto;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private final String status;
    private final String message;

    public ErrorResponse(String status, String message){
        this.status = status;
        this.message = message;
    }

    public ErrorResponse(String status, Exception e){
        this.status = status;
        this.message = e.getMessage();
    }

}
