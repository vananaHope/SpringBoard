package com.subproject.board.dto.auth;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TokenDto {

    private String accessToken;
    private String refreshToken;

    @Getter
    @Setter
    @Builder
    public static class Response {
        private String accessToken;
    }
}
