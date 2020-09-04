package com.c332030.constant.time;

/**
 * <p>
 * Description: DateMillisecondConstants 使用 Long 避免使用时装箱
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
public abstract class MillisecondConstants {

    /**
     * 每秒 毫秒数
     */
    public static final Long MILLIS_OF_SECOND = 1000L;

    /**
     * 每分 毫秒数
     */
    public static final Long MILLIS_OF_MINUTE = SecondConstants.SECOND_OF_MINUTE * MILLIS_OF_SECOND;

    /**
     * 每小时 毫秒数
     */
    public static final Long MILLIS_OF_HOUR = SecondConstants.SECOND_OF_HOUR * MILLIS_OF_SECOND;

    /**
     * 每小时 毫秒数
     */
    public static final Long MILLIS_OF_DAY = SecondConstants.SECOND_OF_DAY * MILLIS_OF_SECOND;

    /**
     * 每小时 毫秒数
     */
    public static final Long MILLIS_OF_WEEK = SecondConstants.SECOND_OF_WEEK * MILLIS_OF_SECOND;
}
