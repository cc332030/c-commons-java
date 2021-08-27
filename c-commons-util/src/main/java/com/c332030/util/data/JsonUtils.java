package com.c332030.util.data;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * <p>
 * Description: JSONUtils
 * </p>
 *
 * @author c332030（袁兴旺）
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtils {

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
        return GsonUtils.toJson(obj);
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
        return GsonUtils.fromJson(jsonStr, tClass);
    }

}
