package com.c332030.util.data;

import java.math.BigDecimal;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * <p>
 * Description: NumberUtils
 * </p>
 *
 * @author c332030（袁兴旺）
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CNumberUtils {

    /**
     * <p>
     * Description: 整数相加
     * </p>
     *
     * @param v1 值1
     * @param v2 值2
     * @return 相加后的值
     * @author c332030
     */
    public static Integer add(Integer v1, Integer v2) {

        if(null == v1) {
            return v2;
        }

        if(null == v2) {
            return v1;
        }

        return v1 + v2;
    }

    /**
     * <p>
     * Description: 浮点数相加
     * </p>
     *
     * @param v1 值1
     * @param v2 值2
     * @return 相加后的值
     * @author c332030
     */
    public static BigDecimal add(BigDecimal v1, BigDecimal v2) {

        if(null == v1) {
            return v2;
        }

        if(null == v2) {
            return v1;
        }

        return v1.add(v2);
    }

    /**
     * <p>
     * Description: 浮点数相加
     * </p>
     *
     * @param v1 值1
     * @param v2 值2
     * @return 相加后的值
     * @author c332030
     */
    public static BigDecimal add(BigDecimal v1, Double v2) {
        return add(v2, v1, null == v1);
    }

    /**
     * <p>
     * Description: 浮点数相加
     * </p>
     *
     * @param v1 值1
     * @param v2 值2
     * @return 相加后的值
     * @author c332030
     */
    public static BigDecimal add(Double v1, Double v2) {
        BigDecimal b1 = null == v1 ? null : BigDecimal.valueOf(v1);
        return add(v2, b1, null == v1);
    }

    /**
     * <p>
     * Description: 获取值
     * </p>
     *
     * @param v2 值2
     * @param b1 值1
     * @param b 值1是否为 null
     * @return 相加后的值
     * @author c332030
     */
    private static BigDecimal add(Double v2, BigDecimal b1, boolean b) {
        BigDecimal b2 = null == v2 ? null : BigDecimal.valueOf(v2);

        if(b) {
            return b2;
        }

        if(null == v2) {
            return b1;
        }

        return b1.add(b2);
    }
}
