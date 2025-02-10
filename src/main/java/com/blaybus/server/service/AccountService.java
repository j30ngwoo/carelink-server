package com.blaybus.server.service;

import com.blaybus.server.common.Validator;
import com.blaybus.server.common.exception.CareLinkException;
import com.blaybus.server.common.exception.ErrorCode;
import com.blaybus.server.config.security.jwt.JwtUtils;
import com.blaybus.server.domain.Member;
import com.blaybus.server.dto.request.AccountDto.LoginRequest;
import com.blaybus.server.dto.request.AccountDto.SignUpRequest;
import com.blaybus.server.dto.response.JwtDto.JwtResponse;
import com.blaybus.server.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AccountService {

    private final Validator validator;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final MemberRepository memberRepository;

    public String joinMember(SignUpRequest request) {
        log.info("join Member");
        if (!validator.checkPassword(request.getPassword(), request.getConfirmPassword())) {
            throw new CareLinkException(ErrorCode.INVALID_CREDENTIALS);
        }

        if (!validator.checkRole(request.getType())) {
            throw new CareLinkException(ErrorCode.INVALID_TYPE);
        }

        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new CareLinkException(ErrorCode.USER_ALREADY_EXISTS);
        }

        Member member = Member.createMember(
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getType()
        );

        memberRepository.save(member);

        return member.getEmail();
    }

    public JwtResponse loginMember(LoginRequest loginRequest) {
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        if (!memberRepository.existsByEmail(username)) {
            throw new CareLinkException(ErrorCode.USER_NOT_FOUND);
        }

        log.info("validateUser: {}, {}", username, password);
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );


        final String jwtToken = jwtUtils.generateJwtToken(authentication);
        final String refreshToken = jwtUtils.generateRefreshToken(authentication);

        final UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();

        return JwtResponse.createJwtResponse(jwtToken, refreshToken, userDetails.getUsername(), roles);
    }
}
