package com.blaybus.server.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Inheritance(strategy = InheritanceType.JOINED)
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

    @Enumerated(EnumType.STRING)
    @Column(name = "login_type")
    private LoginType loginType;

    @Column(name = "name", nullable = false)
    private String name; // 이름

    @Builder
    protected Member(String email, String password, Set<MemberRole> role,
                     LoginType loginType, String name) {
        this.email = email;
        this.password = password;
        this.memberRoles = role;
        this.loginType = loginType;
        this.name = name;
    }
}
