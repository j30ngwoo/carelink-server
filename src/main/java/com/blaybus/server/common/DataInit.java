package com.blaybus.server.common;

import com.blaybus.server.domain.Center;
import com.blaybus.server.repository.CenterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInit implements CommandLineRunner {

    private static final String FILE_PATH = "seoul_center.xlsx";

    private final CenterRepository centerRepository;

    @Override
    public void run(String... args) throws Exception {

        List<Center> centers = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(new File(FILE_PATH));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            int rowCount = sheet.getPhysicalNumberOfRows();

            for (int i = 1; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                String name = row.getCell(0).getStringCellValue();
                String grade = row.getCell(1).getStringCellValue();
                boolean isVehicle = row.getCell(2).getStringCellValue().equals("유") ? true : false;
                String address = row.getCell(3).getStringCellValue();
                String[] addressSplit = address.split(" ");
                String city = addressSplit[1]; // ex: 서울특별시, 충청북도
                String county = addressSplit[2]; // ex: 종로구
                String region = AddressUtils.extractDong(address);
                String tel = row.getCell(4).getStringCellValue();

                LocalDateTime createdAt = DateUtils.generateRandomCreatedAt();

                centers.add(Center.createCenter(name, isVehicle, createdAt, grade, address, tel,
                        city, county, region));
            }
            centerRepository.saveAll(centers);
            log.info("데이터 초기화 완료");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}