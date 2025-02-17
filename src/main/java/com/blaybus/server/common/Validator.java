package com.blaybus.server.common;

import com.blaybus.server.domain.auth.MemberRole;
import org.springframework.stereotype.Component;

@Component
public class Validator {

    public boolean checkPassword(String givenPassword, String confirmPassword) {
        return givenPassword.equals(confirmPassword);
    }

    public boolean checkRole(MemberRole type) {
        if (type != MemberRole.CAREGIVER && type != MemberRole.ADMIN) {
            return false;
        }
        return true;
    }
}
