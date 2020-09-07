package com.c332030.util.data;

import com.c332030.util.asserts.Assert;

/**
 * <p>
 * Description: StringUtils
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
public abstract class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static final String FOUR_SPACE = "    ";

    public static final String EIGHT_SPACE = FOUR_SPACE + FOUR_SPACE;

    /**
     * <p>
     * Description: 通过 Class 生成 key
     * </p>
     *
     * @param cls Class 实例
     * @return 类的唯一 key
     * @author c332030
     */
    public static String getKey(Class<?> cls) {
        Assert.notNull(cls, "Class can't be null");
        return cls.getName();
    }

    /**
     * <p>
     * Description: 通过 Class 生成 key
     * </p>
     *
     * @param cls1 Class 实例
     * @param cls2 Class 实例
     * @return java.lang.String
     * @author c332030
     */
    public static String getKey(Class<?> cls1, Class<?> cls2) {
        return getKey(cls1) + "-" + getKey(cls2);
    }

    /**
     * <p>
     * Description: 大写转驼峰
     * </p>
     *
     * @param str 大写常量字符串
     * @return java.lang.String
     * @author c332030
     */
    public static String toCamelCase(String str) {

        if(isEmpty(str)) {
            return EMPTY;
        }

        String[] strings = str.split("_");
        StringBuilder stringBuilder = new StringBuilder(strings[0].toLowerCase());

        int len = strings.length;
        for(int i = 1; i < len; i++) {
            String subStr = strings[i];

            stringBuilder.append(subStr.charAt(0));

            if(subStr.length() > 1) {
                stringBuilder.append(subStr.substring(1).toLowerCase());
            }
        }

        return stringBuilder.toString();
    }

    /**
     * <p>
     * Description: 转换 string，为 null 时返回 null
     * </p>
     *
     * @param value 值
     * @return java.lang.String
     * @author c332030
     */
    public static String toString(Number value) {
        return value == null ? null : value.toString();
    }


    /**
     * <p>
     * Description: 异常转字符串
     * </p>
     *
     * @param e 异常
     * @return java.lang.String
     * @author c332030
     */
    public static String toString(Throwable e) {
        return toString(e, false);
    }

    /**
     * <p>
     * Description: 异常转字符串
     * </p>
     *
     * @param e 异常
     * @param printSuppressed 是否输出被压抑的异常
     * @return java.lang.String
     * @author c332030
     */
    public static String toString(Throwable e, boolean printSuppressed) {

        if(null == e) {
            return EMPTY;
        }

        final StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(e.getClass().getName());
        stringBuilder.append(": ");
        stringBuilder.append(e.getMessage());
        stringBuilder.append('\n');

        for(StackTraceElement stackTraceElement: e.getStackTrace()) {
            stringBuilder.append(FOUR_SPACE);
            stringBuilder.append(stackTraceElement);
            stringBuilder.append('\n');
        }

        if(printSuppressed) {

            Throwable[] es = e.getSuppressed();
            if(null != es && es.length > 0) {
                int len = es.length;

                for(int i = 0; i < len; i++) {
                    String str = toString(es[i]);
                    if(isEmpty(str)) {
                        continue;
                    }

                    stringBuilder.append("suppressed[");
                    stringBuilder.append(i);
                    stringBuilder.append("] ");
                    stringBuilder.append(str);
                }
            }
        }

        return stringBuilder.toString();
    }

}
