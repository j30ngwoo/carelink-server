package com.blaybus.server.domain;

import com.blaybus.server.domain.senior.Senior;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Center")
public class Center {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "center_id")
    public Long id;

    @Column(name = "center_name", nullable = false)
    private String centerName; // 센터 이름

    @Column(name = "has_bath_vehicle")
    private boolean hasBathVehicle; // 목욕 차량 소유 여부

    @Column(name = "created_at")
    private LocalDateTime createdAt; // 설립년도(이걸로 운영기간 확인)

    @Column(name = "address", nullable = false)
    private String address; // 주소

    @Column(name = "center_rating")
    private String centerRating; // 센터 등급

    @Column(name = "tel")
    private String tel;

    @Column
    private String city;

    @Column
    private String county;

    @Column
    private String region;

    @OneToMany(mappedBy = "center")
    private List<Senior> seniors; // 센터에 소속된 시니어들

    public static Center createCenter(String centerName,
                                      boolean hasBathVehicle,
                                      LocalDateTime createdAt,
                                      String centerRating,
                                      String address,
                                      String tel,
                                      String city,
                                      String county,
                                      String region) {
        return Center.builder()
                .centerName(centerName)
                .hasBathVehicle(hasBathVehicle)
                .createdAt(createdAt)
                .address(address)
                .centerRating(centerRating)
                .tel(tel)
                .city(city)
                .county(county)
                .region(region)
                .build();
    }

}
