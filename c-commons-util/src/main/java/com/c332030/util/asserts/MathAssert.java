package com.c332030.util.asserts;

/**
 * <p>
 * Description: MathAssert
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
public abstract class MathAssert {

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
        Assert.isTrue(num <= Integer.MAX_VALUE, message);
        Assert.isTrue(num >= Integer.MIN_VALUE, message);
    }

}
