package com.blaybus.server.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddressUtils {
    private static final Pattern DONG_PATTERN = Pattern.compile("\\((.*?)\\)");

    private static final Pattern CITY_PATTERN = Pattern.compile("^(.*?시)");
    private static final Pattern COUNTY_PATTERN = Pattern.compile("^(.*?시|.*?도)\\s(.*?구)");
    private static final Pattern REGION_PATTERN = Pattern.compile("\\((.*?)\\)");

    public static String extractDong(String address) {
        Matcher matcher = DONG_PATTERN.matcher(address);
        if (matcher.find()) {
            return matcher.group(1); // 괄호 안의 값 반환
        }
        return null; // 동 정보가 없으면 null 반환
    }

    public static String extractCity(String address) {
        Matcher matcher = CITY_PATTERN.matcher(address);
        return matcher.find() ? matcher.group(1) : null;
    }

    public static String extractCounty(String address) {
        Matcher matcher = COUNTY_PATTERN.matcher(address);
        return matcher.find() ? matcher.group(2) : null;
    }

    public static String extractRegion(String address) {
        Matcher matcher = REGION_PATTERN.matcher(address);
        return matcher.find() ? matcher.group(1) : null;
    }
}
