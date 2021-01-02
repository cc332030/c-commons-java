package com.c332030.util.math;

/**
 * <p>
 * Description: MathUtils
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
public class CMathUtils {

    private CMathUtils() {}

    /**
     * <p>
     * Description: 获取数字长度
     * </p>
     *
     * @param num 数字
     * @return 数字位数
     * @author c332030
     */
    public static int getLength(int num) {
        var len = 1;

        var result = num;
        while ((result = result / 10) != 0){
            len++;
        }

        return len;
    }

    /**
     * <p>
     * Description: 获取数字长度
     * </p>
     *
     * @param num 数字
     * @return 数字位数
     * @author c332030
     */
    public static int getLength(long num) {
        var len = 1;

        var result = num;
        while ((result = result / 10) != 0){
            len++;
        }

        return len;
    }

}
