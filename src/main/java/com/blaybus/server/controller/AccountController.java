package com.blaybus.server.controller;

import com.blaybus.server.dto.ResponseFormat;
import com.blaybus.server.dto.request.AccountDto.*;
import com.blaybus.server.dto.response.JwtDto.JwtResponse;
import com.blaybus.server.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tags(value = @Tag(name = "AccountController", description = "Certification and Accreditation"))
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/sign-up")
    @Operation(summary = "Sign up as a new user")
    public ResponseEntity<ResponseFormat<String>> signup(@RequestBody SignUpRequest signUpRequest) {
        log.info("member create");
        String createdUsername = accountService.joinMember(signUpRequest);

        ResponseFormat<String> response = new ResponseFormat<>(
                HttpStatus.OK.value(),
                "Member registered successfully (username: " + createdUsername + ")",
                createdUsername
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    @Operation(summary = "Log in as an existing user")
    public ResponseEntity<ResponseFormat<JwtResponse>> signIn(@RequestBody LoginRequest loginRequest) {
        log.info("member sign in");
        JwtResponse loginResult = accountService.loginMember(loginRequest);

        ResponseFormat<JwtResponse> response = new ResponseFormat<>(
                HttpStatus.OK.value(),
                "Login successful",
                loginResult
        );

        return ResponseEntity.ok(response);
    }
}
