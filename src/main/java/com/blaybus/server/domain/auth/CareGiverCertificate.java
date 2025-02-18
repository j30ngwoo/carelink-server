package com.blaybus.server.domain.auth;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "caregiver_certificates")
public class CareGiverCertificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "caregiver_id", nullable = false)
    private CareGiver careGiver;

    @Enumerated(EnumType.STRING)
    @Column(name = "caregiver_type", nullable = false)
    private CareGiverType careGiverType; // 요양보호사, 사회복지사 등

    @Column(name = "certificated_number", nullable = false)
    private String certificatedNumber; // 자격증 번호

    public CareGiverCertificate(CareGiver careGiver, CareGiverType type, String certificatedNumber) {
        this.careGiver = careGiver;
        this.careGiverType = type;
        this.certificatedNumber = certificatedNumber;
    }
}
