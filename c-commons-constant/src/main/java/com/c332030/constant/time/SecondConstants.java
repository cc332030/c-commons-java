package com.c332030.constant.time;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * <p>
 * Description: DateSecondConstants 使用 Long 避免使用时装箱
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecondConstants {

    /**
     * 每分 秒数
     */
    public static final Long SECOND_OF_MINUTE = 60L;

    /**
     * 每小时 秒数
     */
    public static final Long SECOND_OF_HOUR = SECOND_OF_MINUTE * 60;

    /**
     * 每小时 秒数
     */
    public static final Long SECOND_OF_DAY = SECOND_OF_HOUR * 24;

    /**
     * 每小时 秒数
     */
    public static final Long SECOND_OF_WEEK = SECOND_OF_DAY * 7;
}
