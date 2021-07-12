package com.c332030.util.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

import javax.annotation.Nonnull;

import org.apache.commons.lang3.time.FastDateFormat;

import com.google.common.collect.ImmutableList;

import com.c332030.constant.date.DateFormatConstants;
import com.c332030.constant.time.TimeConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * <p>
 * Description: DateUtils
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CDateUtils {

    /**
     * <p>
     * Description: 新实例
     * </p>
     *
     * @param pattern 格式
     * @return java.text.DateFormat
     * @author c332030
     */
    public static DateFormat newDateFormat(@Nonnull String pattern) {
        return new SimpleDateFormat(pattern);
    }

    /**
     * <p>
     * Description: 新实例
     * </p>
     *
     * @param pattern 格式
     * @return org.apache.commons.lang3.time.FastDateFormat
     * @author c332030
     */
    public static FastDateFormat newFastDateFormat(@Nonnull String pattern) {
        return FastDateFormat.getInstance(pattern);
    }

    public static int indexToZone(int i) {
        return i - 11;
    }
    public static int zoneToIndex(int i) {
        return i + 11;
    }

    public static final List<String> ZONE_ID_LIST;
    static {
        ImmutableList.Builder<String> builder = ImmutableList.builder();
        int i = 0;
        while (i < 24) {

            int zone = indexToZone(i++);
            String zoneStr = TimeConstants.UTC;
            if(zone > 0) {
                zoneStr += "+" + zone;
            } else if(zone < 0) {
                zoneStr += "-" + Math.abs(zone);
            }

            builder.add(zoneStr);
        }
        ZONE_ID_LIST = builder.build();
    }

    public static String getZoneIdStr(int zone) {
        return ZONE_ID_LIST.get(zoneToIndex(zone));
    }

    /**
     * ZoneIdString - ZoneId map
     */
    public static final Map<String, ZoneId> ZONE_ID_MAP = new ConcurrentHashMap<>();

    /**
     * <p>
     * Description: 获取 ZoneId
     * </p>
     *
     * @param zoneId zoneId String
     * @return java.time.ZoneId
     * @author c332030
     */
    public static ZoneId getZoneId(@Nonnull String zoneId) {
        return ZONE_ID_MAP.computeIfAbsent(zoneId, ZoneId::of);
    }

    public static ZoneId getZoneId(int zone) {
        return getZoneId(getZoneIdStr(zone));
    }

    /**
     * <p>
     * Description: 新实例
     * </p>
     *
     * @param pattern 格式
     * @return java.time.format.DateTimeFormatter
     * @author c332030
     */
    public static DateTimeFormatter newDateTimeFormatter(@Nonnull String pattern) {
        return newDateTimeFormatter(pattern, Locale.CHINA, TimeConstants.UTC8);
    }

    /**
     * <p>
     * Description: 新实例
     * </p>
     *
     * @param pattern 格式
     * @param locale 区域
     * @param zoneId 时区
     * @return java.time.format.DateTimeFormatter
     * @author c332030
     */
    public static DateTimeFormatter newDateTimeFormatter(
        @Nonnull String pattern,
        @Nonnull Locale locale,
        @Nonnull String zoneId
    ) {
        return newDateTimeFormatter(pattern, locale, getZoneId(zoneId));
    }


    /**
     * <p>
     * Description: 新实例
     * </p>
     *
     * @param pattern 格式
     * @param locale 区域
     * @param zoneId 时区
     * @return java.time.format.DateTimeFormatter
     * @author c332030
     */
    public static DateTimeFormatter newDateTimeFormatter(
        @Nonnull String pattern,
        @Nonnull Locale locale,
        @Nonnull ZoneId zoneId
    ) {
        return DateTimeFormatter.ofPattern(pattern)
            .withLocale(locale)
            .withZone(zoneId);
    }

    /**
     * pattern-DateFormat map
     */
    private static final Map<String, ThreadLocal<DateFormat>> DATE_FORMAT_MAP = new ConcurrentHashMap<>();

    /**
     * <p>
     * Description: 获取 DateFormat
     * </p>
     *
     * @param pattern 表达式
     * @return java.text.DateFormat
     * @author c332030
     */
    public static DateFormat getDateFormat(@Nonnull String pattern) {
        return DATE_FORMAT_MAP.computeIfAbsent(pattern,
            (e) -> ThreadLocal.withInitial(() -> newDateFormat(pattern))
        ).get();
    }

    /**
     * pattern-FastDateFormat map
     */
    private static final Map<String, FastDateFormat> FAST_DATE_FORMAT_MAP = new ConcurrentHashMap<>();

    /**
     * <p>
     * Description: 获取 FastDateFormat
     * </p>
     *
     * @param pattern 表达式
     * @return org.apache.commons.lang3.time.FastDateFormat
     * @author c332030
     */
    public static FastDateFormat getFastDateFormat(@Nonnull String pattern) {
        return FAST_DATE_FORMAT_MAP.computeIfAbsent(pattern, (e) -> newFastDateFormat(pattern));
    }

    /**
     * pattern-DateTimeFormatter map
     */
    private static final Map<String, DateTimeFormatter> DATE_TIME_FORMATTER_MAP = new ConcurrentHashMap<>();

    /**
     * <p>
     * Description: 获取 DateTimeFormatter
     * </p>
     *
     * @param pattern 表达式
     * @return java.time.format.DateTimeFormatter
     * @author c332030
     */
    public static DateTimeFormatter getDateTimeFormatter(@Nonnull String pattern) {
        return getDateTimeFormatter(pattern, Locale.CHINA, TimeConstants.UTC8);
    }

    /**
     * <p>
     * Description: 获取 DateTimeFormatter
     * </p>
     *
     * @param pattern 格式
     * @param locale 区域
     * @param zoneId 时区
     * @return java.time.format.DateTimeFormatter
     * @author c332030
     */
    public static DateTimeFormatter getDateTimeFormatter(@Nonnull String pattern, Locale locale, int zoneId) {
        return getDateTimeFormatter(pattern, locale, getZoneId(zoneId));
    }

    /**
     * <p>
     * Description: 获取 DateTimeFormatter
     * </p>
     *
     * @param pattern 格式
     * @param locale 区域
     * @param zoneId 时区
     * @return java.time.format.DateTimeFormatter
     * @author c332030
     */
    public static DateTimeFormatter getDateTimeFormatter(@Nonnull String pattern, Locale locale, String zoneId) {
        return getDateTimeFormatter(pattern, locale, getZoneId(zoneId));
    }

    /**
     * <p>
     * Description: 获取 DateTimeFormatter
     * </p>
     *
     * TODO 国际化
     * @param pattern 格式
     * @param locale 区域
     * @param zoneId 时区
     * @return java.time.format.DateTimeFormatter
     * @author c332030
     */
    public static DateTimeFormatter getDateTimeFormatter(@Nonnull String pattern, Locale locale, ZoneId zoneId) {
        return DATE_TIME_FORMATTER_MAP.computeIfAbsent(pattern,
            (e) -> newDateTimeFormatter(pattern, locale, zoneId));
    }

    public static final FastDateFormat FAST_DATE_FORMAT = getFastDateFormat(DateFormatConstants.DATE_FORMAT_STR);
    public static final FastDateFormat FAST_DATE_TIME_FORMAT = getFastDateFormat(DateFormatConstants.DATE_TIME_FORMAT_STR);

    /**
     * <p>
     * Description: 格式化日期 yyyy-MM-dd
     * </p>
     *
     * @param date 日期
     * @param pattern 格式
     * @return java.lang.String
     * @author c332030
     */
    public static String format(@Nonnull Date date, @Nonnull String pattern) {
        return getFastDateFormat(pattern).format(date);
    }

    /**
     * <p>
     * Description: 格式化日期 yyyy-MM-dd
     * </p>
     *
     * @param date 日期
     * @return java.lang.String
     * @author c332030
     */
    public static String formatDate(@Nonnull Date date) {
        return FAST_DATE_FORMAT.format(date);
    }

    /**
     * <p>
     * Description: "yyyy-MM-dd HH:mm:ss";
     * </p>
     *
     * @param date 日期时间
     * @return java.lang.String
     * @author c332030
     */
    public static String formatDateTime(@Nonnull Date date) {
        return FAST_DATE_TIME_FORMAT.format(date);
    }

    public static final DateTimeFormatter DATE_FORMATTER = getDateTimeFormatter(DateFormatConstants.DATE_FORMAT_STR);
    public static final DateTimeFormatter DATE_TIME_FORMATTER = getDateTimeFormatter(DateFormatConstants.DATE_TIME_FORMAT_STR);

    /**
     * <p>
     * Description: 格式化日期 yyyy-MM-dd
     * </p>
     *
     * @param temporalAccessor 日期
     * @return java.lang.String
     * @author c332030
     */
    public static String formatDate(@Nonnull TemporalAccessor temporalAccessor) {
        return DATE_FORMATTER.format(temporalAccessor);
    }

    /**
     * <p>
     * Description: "yyyy-MM-dd HH:mm:ss";
     * </p>
     *
     * @param temporalAccessor 日期时间
     * @return java.lang.String
     * @author c332030
     */
    public static String formatDateTime(@Nonnull TemporalAccessor temporalAccessor) {
        return DATE_TIME_FORMATTER.format(temporalAccessor);
    }

    /**
     * <p>
     * Description: 生成时间区间内每一天（日期对象）
     * </p>
     *
     * @param dateStart 日期区间开始
     * @param dateEnd 日期区间结束
     * @return 日期数组
     * @author c332030
     */
    public static List<Date> getEveryDayOfBetween(Date dateStart, Date dateEnd) {
        final List<Date> list = new ArrayList<>();
        generateEveryDayOfBetween(dateStart, dateEnd, list::add, null);
        return list;
    }

    /**
     * <p>
     * Description: 生成时间区间内每一天（字符串）
     * </p>
     *
     * @param dateStart 日期区间开始
     * @param dateEnd 日期区间结束
     * @return 日期数组
     * @author c332030
     */
    public static List<String> getEveryDayOfBetweenForStr(Date dateStart, Date dateEnd) {
        final List<String> list = new ArrayList<>();
        generateEveryDayOfBetween(dateStart, dateEnd, null, list::add);
        return list;
    }

    /**
     * <p>
     * Description: 生成时间区间内每一天
     * </p>
     *
     * @param dateStart 日期区间开始
     * @param dateEnd 日期区间结束
     * @param dateConsumer 消费
     * @param stringConsumer 消费
     * @author c332030（袁兴旺）
     */
    public static void generateEveryDayOfBetween(
        Date dateStart, Date dateEnd,
        Consumer<Date> dateConsumer,
        Consumer<String> stringConsumer
    ) {

        if(dateStart.getTime() >= dateEnd.getTime()) {
            throw new IllegalArgumentException("dateStart must early than dateEnd");
        }

        String dateStartStr = formatDate(dateStart);
        String dateEndStr = formatDate(dateEnd);

        if(dateStartStr.equals(dateEndStr)) {
            everyDayOfBetweenDataPush(dateConsumer, stringConsumer, null, dateStartStr);
            return;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateStart);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Date date = calendar.getTime();
        String dateStr = formatDate(date);

        everyDayOfBetweenDataPush(dateConsumer, stringConsumer, date, dateStr);

        while (!dateEndStr.equals(dateStr)) {

            calendar.add(Calendar.DAY_OF_MONTH, 1);
            date = calendar.getTime();
            dateStr = formatDate(date);

            everyDayOfBetweenDataPush(dateConsumer, stringConsumer, date, dateStr);
        }
    }

    private static void everyDayOfBetweenDataPush(
        Consumer<Date> dateConsumer,
        Consumer<String> stringConsumer,
        Date date, String dateStr
    ) {
        if(null != dateConsumer) {
            if(null == date) {
                // date = parseDate(dateStr);
            }
            dateConsumer.accept(date);
        }
        if(null != stringConsumer) {
            stringConsumer.accept(dateStr);
        }
    }

    /**
     * <p>
     * Description: 获取当天年龄为 age 的出生日期
     * </p>
     *
     * @param age 年龄
     * @return java.util.Date
     * @author c332030（袁兴旺）
     */
    public static Date getDateByAge(int age) {
        return getDateByAge(age, new Date());
    }

    /**
     * <p>
     * Description: 获取日期为 date 时，年龄为 age 的出生日期
     * </p>
     *
     * @param age 年龄
     * @param date 日期
     * @return java.util.Date
     * @author c332030（袁兴旺）
     */
    public static Date getDateByAge(int age, Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        calendar.add(Calendar.YEAR, age * -1);

        return calendar.getTime();
    }

}
