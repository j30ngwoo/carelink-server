package com.blaybus.server.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public record JwtDto() {
    @Data
    @Builder
    @AllArgsConstructor
    public static final class JwtResponse {
        private String accessToken;
        private String refreshToken;
        private String username;
        private List<String> roles;

        public static JwtResponse createJwtResponse(String accessToken, String refreshToken, String username, List<String> roles) {
            return JwtResponse.builder()
                    .accessToken("Bearer " + accessToken)
                    .refreshToken(refreshToken)
                    .username(username)
                    .roles(roles)
                    .build();
        }
    }
}
