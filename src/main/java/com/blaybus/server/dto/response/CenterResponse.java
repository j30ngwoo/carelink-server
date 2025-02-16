package com.blaybus.server.dto.response;

import com.blaybus.server.domain.Center;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Builder
public class CenterResponse {

    private List<CenterInfo> centers;

    @Data
    @Builder
    @AllArgsConstructor
    public static class CenterInfo {
        private Long id;
        private String centerName; // 센터 이름
        private boolean hasBathVehicle; // 목욕 차량 소유 여부
        private LocalDateTime createdAt; // 설립년도(이걸로 운영기간 확인)
        private String address; // 주소
        private String centerRating; // 센터 등급
        private String tel;
    }

    public static CenterInfo createCenterInfo(Center center) {
        return CenterInfo.builder()
                .id(center.getId())
                .centerName(center.getCenterName())
                .hasBathVehicle(center.isHasBathVehicle())
                .createdAt(center.getCreatedAt())
                .address(center.getAddress())
                .centerRating(center.getCenterRating())
                .tel(center.getTel())
                .build();
    }

    public static CenterResponse createListResponse(List<Center> centerList) {
        List<CenterInfo> centerResponses = centerList.stream()
                .map(CenterResponse::createCenterInfo)
                .collect(Collectors.toList());

        return CenterResponse.builder()
                .centers(centerResponses)
                .build();
    }
}