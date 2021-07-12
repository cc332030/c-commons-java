package com.c332030.util.bean;

import org.apache.commons.lang3.StringUtils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * <p>
 * Description: ClassUtils
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CClassUtils {

    /**
     * <p>
     * Description: get class name in spring bean
     * </p>
     *
     * @param clazz class
     * @return class name in spring bean
     * @author c332030
     */
    public static String getSimpleNameUnCapitalize(Class<?> clazz) {
        return getSimpleNameUnCapitalize(getSimpleName(clazz));
    }

    /**
     * <p>
     * Description: get class name in spring bean
     * </p>
     *
     * @param className class name
     * @return class name in spring bean
     * @author c332030
     */
    public static String getSimpleNameUnCapitalize(String className) {
        return StringUtils.uncapitalize(getSimpleName(className));
    }

    /**
     * <p>
     * Description: get class simple name
     * </p>
     *
     * @param clazz class
     * @return class simple name
     * @author c332030
     */
    public static String getSimpleName(Class<?> clazz) {
        return getSimpleName(clazz.getName());
    }

    private static final char POINT = '.';

    private static final char DOLLAR = '$';

    /**
     * <p>
     * Description: get class simple name
     * </p>
     *
     * @param className class name
     * @return class simple name
     * @author c332030
     */
    public static String getSimpleName(String className) {

        int pointIndex = className.lastIndexOf(POINT);
        if(-1 == pointIndex) {
            pointIndex = 0;
        } else {
            pointIndex++;
        }

        int dollarIndex = className.indexOf(DOLLAR);
        return -1 == dollarIndex
            ? className.substring(pointIndex)
            : className.substring(pointIndex, dollarIndex);
    }

}
