package com.blaybus.server.dto.request;

import com.blaybus.server.domain.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public record AccountDto() {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static final class SignUpRequest {
        private String email;
        private String password;
        private String confirmPassword;
        private MemberRole type;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static final class LoginRequest {
        private String email;
        private String password;
    }
}

