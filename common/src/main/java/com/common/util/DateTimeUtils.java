package com.common.util;


import com.common.context.StringPool;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DateTimeUtils {

    private static final Map<String, DateTimeFormatter> FORMATTERS = new ConcurrentHashMap<>();

    private DateTimeUtils() {
        throw new IllegalStateException(StringPool.UTILITY_CLASS);
    }

    public static String generalFormatNow() {
        return generalFormat(LocalDateTime.now());
    }

    public static String generalFormat(LocalDateTime localDateTime) {
        return ofPattern(StringPool.DATETIME).format(localDateTime);
    }
    
    public static DateTimeFormatter ofPattern(String pattern) {
        return FORMATTERS.computeIfAbsent(pattern, DateTimeFormatter::ofPattern);
    }

}
