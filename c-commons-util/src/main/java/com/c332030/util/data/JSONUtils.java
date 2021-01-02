package com.c332030.util.data;

/**
 * <p>
 * Description: JSONUtils
 * </p>
 *
 * @author c332030（袁兴旺）
 * @version 1.0
 */
public class JSONUtils {

    private JSONUtils() {}

    /**
     * <p>
     * Description: 转 JSON
     * </p>
     *
     * @param obj 对象
     * @return java.lang.String
     * @author c332030（袁兴旺）
     */
    public static String toJson(Object obj) {
        return GSONUtils.toJson(obj);
    }

    /**
     * <p>
     * Description: json 转对象
     * </p>
     *
     * @param jsonStr json 字符串
     * @param tClass 实体类
     * @param <T> 类型
     * @return 实体类对象
     * @author c332030
     */
    public static <T> T fromJson(String jsonStr, Class<T> tClass) {
        return GSONUtils.fromJson(jsonStr, tClass);
    }

}
