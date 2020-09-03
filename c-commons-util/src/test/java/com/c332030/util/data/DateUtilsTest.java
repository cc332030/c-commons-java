package com.c332030.util.data;

import java.time.ZoneId;
import java.util.Locale;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

import static com.c332030.constant.sys.date.DateFormatConstants.DATE_STR;
import static com.c332030.util.data.DateUtils.getZoneId;
import static com.c332030.util.data.DateUtils.zoneToIndex;

/**
 * <p>
 * Description: DateUtilsTest
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
@Slf4j
public class DateUtilsTest {

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

}
