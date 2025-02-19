package com.blaybus.server.service;

import com.blaybus.server.common.AddressUtils;
import com.blaybus.server.common.exception.CareLinkException;
import com.blaybus.server.common.exception.ErrorCode;
import com.blaybus.server.domain.Center;
import com.blaybus.server.domain.auth.CareGiver;
import com.blaybus.server.domain.auth.GenderType;
import com.blaybus.server.domain.senior.Senior;
import com.blaybus.server.dto.request.CenterRequest.*;
import com.blaybus.server.dto.response.CareGiverResponse;
import com.blaybus.server.dto.response.CenterResponse;
import com.blaybus.server.dto.response.MyPageResponse;
import com.blaybus.server.repository.CareGiverRepository;
import com.blaybus.server.repository.CenterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CenterService {
    private final CenterRepository centerRepository;
    private final CareGiverRepository careGiverRepository;

    public CenterResponse findCentersByDong(CenterGetRequest centerRequest) {
        String city = centerRequest.getCity();
        String county = centerRequest.getCounty();
        String region = centerRequest.getRegion();
        log.info("센터 정보 조회: {} {} {}", city, county, region);
        if (city == null) {
            throw new CareLinkException(ErrorCode.NO_ADDRESS);
        }

        List<Center> centerList = centerRepository.findByAddressContaining(city, county, region);

        return CenterResponse.createListResponse(centerList);
    }

    @Transactional(readOnly = true)
    public CenterResponse findCentersByName(String name) {
        if (name == null || name.length() >= 30) {
            throw new CareLinkException(ErrorCode.INVALID_CENTER_NAME);
        }
        List<Center> centers = centerRepository.findByCenterNameContaining(name);
        return CenterResponse.createListResponse(centers);
    }

    @Transactional(readOnly = true)
    public CenterResponse.CenterInfo findCenterById(Long centerId) {
        Center center = centerRepository.findById(centerId)
                .orElseThrow(() -> new CareLinkException(ErrorCode.CENTER_NOT_FOUND));

        return CenterResponse.createCenterInfo(center);
    }

    public Long saveCenter(CenterPostRequest centerPostRequest) {
        log.info("센터 등록 시도");

        String city = AddressUtils.extractCity(centerPostRequest.getAddress());
        String county = AddressUtils.extractCounty(centerPostRequest.getAddress());
        String region = AddressUtils.extractRegion(centerPostRequest.getAddress());

        Center center = Center.createCenter(
                centerPostRequest.getName(),
                centerPostRequest.isHasBathVehicle(),
                centerPostRequest.getCreatedAt(),
                centerPostRequest.getCenterRating(),
                centerPostRequest.getAddress(),
                centerPostRequest.getTel(),
                city,
                county,
                region
        );

        centerRepository.save(center);
        log.info("센터 등록 성공: {}", center.getCenterName());
        return center.getId();
    }

    @Transactional
    public String updateCenter(Long centerId, CenterUpdateRequest updateRequest) {
        Center center = centerRepository.findById(centerId)
                .orElseThrow(() -> new CareLinkException(ErrorCode.CENTER_NOT_FOUND));

        center.setCenterName(updateRequest.getName());
        center.setHasBathVehicle(updateRequest.isHasBathVehicle());
        center.setCreatedAt(updateRequest.getCreatedAt());
        center.setAddress(updateRequest.getAddress());
        center.setCenterRating(updateRequest.getCenterRating());
        center.setTel(updateRequest.getTel());

        return center.getCenterName();
    }

    @Transactional
    public void deleteCenter(Long centerId) {
        if (!centerRepository.existsById(centerId)) {
            throw new CareLinkException(ErrorCode.CENTER_NOT_FOUND);
        }
        centerRepository.deleteById(centerId);
    }

    // 효주 추가
    public CenterResponse findSeniorsByCenterId(Long centerId) {
        log.info("센터 ID로 어르신 조회: {}", centerId);
        Center center = centerRepository.findById(centerId)
                .orElseThrow(() -> new CareLinkException(ErrorCode.CENTER_NOT_FOUND));

        List<Senior> seniors = center.getSeniors();
        return CenterResponse.createSeniorListResponse(seniors);
    }

    public List<String> findCareGiversByConditions(Long centerId, String workingRegion, GenderType preferGenderType) {
        log.info("센터 ID로 요양보호사 조회: {}", centerId);
        Center center = centerRepository.findById(centerId)
                .orElseThrow(() -> new CareLinkException(ErrorCode.CENTER_NOT_FOUND));

        List<CareGiver> careGivers = careGiverRepository.findAll();

        return careGivers.stream()
                .map(careGiver -> {
                    int score = 0;
                    if (careGiver.getRegion().equals(workingRegion)) {
                        score += 5;
                    }
                    if (careGiver.getGenderType() == preferGenderType) {
                        score += 5;
                    }
                    return CareGiverResponse.fromEntity(careGiver, score);
                })
                .sorted((cg1, cg2) -> Integer.compare(cg2.getScore(), cg1.getScore()))
                .map(CareGiverResponse::getCareGiver)
                .collect(Collectors.toList());
    }
}
