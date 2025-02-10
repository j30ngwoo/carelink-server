package com.blaybus.server.common;

import com.blaybus.server.domain.Center;
import com.blaybus.server.repository.CenterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInit implements CommandLineRunner {

    private final CenterRepository centerRepository;

    @Override
    public void run(String... args) throws Exception {
        Center center1 = Center.createCenter(
                "센터1",
                true,
                "20180421",
                3.5,
                "서울특별시 종로구"
        );

        Center center2 = Center.createCenter(
                "센터2",
                true,
                "20210311",
                3.9,
                "서울특별시 동작구"
        );

        Center center3 = Center.createCenter(
                "센터3",
                false,
                "20240513",
                4.0,
                "경기도 수원시"
        );

        centerRepository.save(center1);
        centerRepository.save(center2);
        centerRepository.save(center3);

        log.info("데이터 초기화 완료");
    }
}