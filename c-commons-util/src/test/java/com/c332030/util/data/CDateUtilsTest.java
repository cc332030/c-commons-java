package com.c332030.util.data;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Locale;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

import static com.c332030.constant.date.DateFormatConstants.DATE_STR;
import static com.c332030.util.data.CDateUtils.*;

/**
 * <p>
 * Description: DateUtilsTest
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
@Slf4j
public class CDateUtilsTest {

    @Test
    public void zoneTest() {

        System.out.println(zoneToIndex(-11));
        System.out.println(zoneToIndex(0));
        System.out.println(zoneToIndex(12));

        System.out.println(getZoneId(-11));
        System.out.println(getZoneId(0));
        System.out.println(getZoneId(12));
    }

    @Test
    public void dateTimeFormatterMapKeyTest() {
        System.out.println(DATE_STR + Locale.CHINA + ZoneId.of("UTC+8"));
    }

    @Test
    public void testStringToInstant() {

        Instant instant = Instant.from(DATE_TIME_FORMATTER.parse("2021-01-01 00:00:00"));

        System.out.println(instant.toEpochMilli());

        System.out.println(System.currentTimeMillis());
    }

}
