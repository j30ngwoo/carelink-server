package com.blaybus.server.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Member", indexes = {
        @Index(name = "idx_member_email", columnList = "email", unique = true),
})
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password")
    private String password;

    @ElementCollection(targetClass = MemberRole.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Set<MemberRole> memberRoles;

    public static Member createMember(String email, String encodedPassword, MemberRole type) {
        return Member.builder()
                .email(email)
                .password(encodedPassword)
                .memberRoles(Set.of(
                        type == MemberRole.CAREGIVER ?
                                MemberRole.CAREGIVER : MemberRole.ADMIN
                ))
                .build();
    }
}
