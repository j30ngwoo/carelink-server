package com.blaybus.server.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public record CenterRequest() {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static final class CenterGetRequest {
        @NotNull(message = "시 이름은 필수입니다.")
        private String city;
        @NotNull(message = "구는 필수입니다.")
        private String county;
        @NotNull(message = "동은 필수입니다.")
        private String region;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static final class CenterPostRequest {
        @NotNull(message = "센터 이름은 필수입니다.")
        private String name;
        @NotNull(message = "목욕 차량 여부는 필수입니다.")
        private boolean hasBathVehicle;
        @NotNull(message = "설립 일자는 필수입니다.")
        private LocalDateTime createdAt;
        @NotNull(message = "주소는 필수입니다.")
        private String address;
        private String centerRating;
        @NotNull(message = "전화번호는 필수입니다.")
        private String tel;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static final class CenterUpdateRequest {
        private String name;           // 센터 이름
        private boolean hasBathVehicle; // 목욕 차량 여부
        private LocalDateTime createdAt; // 설립일자
        private String address;        // 주소
        private String centerRating;   // 센터 등급
        private String tel;            // 전화번호
    }



}
