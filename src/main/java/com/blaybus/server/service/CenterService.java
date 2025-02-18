package com.blaybus.server.service;

import com.blaybus.server.common.AddressUtils;
import com.blaybus.server.common.exception.CareLinkException;
import com.blaybus.server.common.exception.ErrorCode;
import com.blaybus.server.domain.Center;
import com.blaybus.server.dto.request.CenterRequest.*;
import com.blaybus.server.dto.response.CenterResponse;
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
}
