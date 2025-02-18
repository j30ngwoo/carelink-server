package com.blaybus.server.domain.auth;

import com.blaybus.server.domain.Center;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "caregiver_experiences")
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "caregiver_id", nullable = false)
    private CareGiver careGiver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "center_id", nullable = false)
    private Center center;

    @Column(name = "certificated_at", nullable = false)
    private LocalDate certificatedAt; // 시작 경력

    @Column(name = "end_certificated_at")
    private LocalDate endCertificatedAt; // 마지막 경력

    @Column(name = "assigned_task", nullable = false)
    private String assignedTask; // 담당 업무

    public Experience(CareGiver careGiver, Center center, LocalDate certificatedAt,
                      LocalDate endCertificatedAt, String assignedTask) {
        this.careGiver = careGiver;
        this.center = center;
        this.certificatedAt = certificatedAt;
        this.endCertificatedAt = endCertificatedAt;
        this.assignedTask = assignedTask;
    }
}
