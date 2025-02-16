package com.blaybus.server.controller;

import com.blaybus.server.domain.Center;
import com.blaybus.server.dto.ResponseFormat;
import com.blaybus.server.dto.request.CenterRequest.*;
import com.blaybus.server.dto.response.CenterResponse;
import com.blaybus.server.service.CenterService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/center")
@Tags(value = @Tag(name = "CenterController", description = "lookup center information"))
public class CenterController {

    private final CenterService centerService;

    @GetMapping
    public ResponseEntity<ResponseFormat<CenterResponse>> getCentersByDong(@ModelAttribute CenterGetRequest centerRequest) {
        CenterResponse centerResponses = centerService.findCentersByDong(centerRequest);

        ResponseFormat<CenterResponse> response = new ResponseFormat<>(
                HttpStatus.OK.value(),
                "센터 정보 조회 성공",
                centerResponses
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{centerId}")
    public ResponseEntity<ResponseFormat<CenterResponse.CenterInfo>> getCenterById(@PathVariable Long centerId) {
        CenterResponse.CenterInfo centerInfo = centerService.findCenterById(centerId);

        ResponseFormat<CenterResponse.CenterInfo> response = new ResponseFormat<>(
                HttpStatus.OK.value(),
                "센터 조회 성공",
                centerInfo
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ResponseFormat<Long>> registrationCenter(@RequestBody CenterPostRequest centerRequest) {
        Long newCenterId = centerService.saveCenter(centerRequest);

        ResponseFormat<Long> response = new ResponseFormat<>(
                HttpStatus.OK.value(),
                "센터 등록 성공",
                newCenterId
        );

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{centerId}")
    public ResponseEntity<ResponseFormat<String>> updateCenter(
            @PathVariable Long centerId,
            @RequestBody CenterUpdateRequest updateRequest) {

        String updatedCenterName = centerService.updateCenter(centerId, updateRequest);

        ResponseFormat<String> response = new ResponseFormat<>(
                HttpStatus.OK.value(),
                "센터 정보 수정 성공",
                updatedCenterName
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{centerId}")
    public ResponseEntity<ResponseFormat<Void>> deleteCenter(@PathVariable Long centerId) {
        centerService.deleteCenter(centerId);

        ResponseFormat<Void> response = new ResponseFormat<>(
                HttpStatus.OK.value(),
                "센터 삭제 성공",
                null
        );

        return ResponseEntity.ok(response);
    }
}
