package com.c332030.util.asserts;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * <p>
 * Description: MathAssert
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MathAssert {

    /**
     * <p>
     * Description: 超过最大 int 范围则报错
     * </p>
     *
     * @param num 数字
     * @param message 报错
     * @author c332030
     */
    public static void overMaxInteger(long num, String message) {
        CAssert.isTrue(num <= Integer.MAX_VALUE, message);
        CAssert.isTrue(num >= Integer.MIN_VALUE, message);
    }

}
