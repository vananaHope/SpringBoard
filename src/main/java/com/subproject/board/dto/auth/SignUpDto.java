package com.subproject.board.dto.auth;

import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SignUpDto {
    @Getter
    @Setter
    public static class Request {

        @NotBlank
        @Size(min = 3, max = 50)
        private String userId;

        @NotBlank
        @Size(min = 3, max = 50)
        private String userPassword;

        @NotBlank
        @Size(min = 3, max = 50)
        private String userNickname;

        @NotBlank
        @Email
        @Size(min = 3, max = 50)
        private String userEmail;

    }

    @Getter
    @Setter
    @Builder
    public static class Response {
        private String userId;
        private String userNickname;
        private String accessToken;
    }
}