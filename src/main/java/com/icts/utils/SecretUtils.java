package com.icts.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Slf4j
public class SecretUtils {
    private static final String SECRET_KEY = "admin";

    public static Boolean check(String secret) {
        log.info("secret:{}", secret);
        if (StringUtils.isBlank(secret) || !StringUtils.startsWith(secret, SECRET_KEY)) {
            return Boolean.FALSE;
        }
        String substring = secret.substring(5);
        long current;
        try {
            current = Long.parseLong(substring);
        } catch (Exception e) {
            return Boolean.FALSE;
        }
        // 获取上海时区
        ZoneId shanghaiZone = ZoneId.of("Asia/Shanghai");

        // 获取当天开始时间（上海时间）
        ZonedDateTime startOfDayShanghai = LocalDateTime.now().atZone(shanghaiZone).toLocalDate().atStartOfDay().atZone(shanghaiZone);
        long startTimestamp = startOfDayShanghai.toInstant().toEpochMilli();
        log.info("startTime:{}", secret);

        // 获取当天结束时间（上海时间）
        ZonedDateTime endOfDayShanghai = LocalDateTime.now().atZone(shanghaiZone).toLocalDate().atTime(23, 59, 59).atZone(shanghaiZone);
        long endTimestamp = endOfDayShanghai.toInstant().toEpochMilli();
        log.info("entTime:{}", secret);

        return current >= startTimestamp && current <= endTimestamp;
    }
}
