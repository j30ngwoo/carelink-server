package com.blaybus.server.service;

import com.blaybus.server.common.Validator;
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
            throw new RuntimeException("비밀번호가 다릅니다");
        }

        if (!validator.checkRole(request.getType())) {
            throw new RuntimeException("유저 타입이 잘못 되었습니다");
        }

        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("중복된 유저입니다.");
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

        log.info("validateUser: {}, {}", username, password);
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );


        final String jwtToken = jwtUtils.generateJwtToken(authentication);
        final String refreshToken = jwtUtils.generateRefreshToken(authentication);

        final UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();
        return new JwtResponse(jwtToken, refreshToken, userDetails.getUsername(), roles);
    }
}
