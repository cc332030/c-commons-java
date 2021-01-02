package com.c332030.util.data;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * Description: GsonUtils
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
@Slf4j
public class GSONUtils {

    private static final Gson GSON = new Gson();

    private GSONUtils() {}

    /**
     * <p>
     * Description: 转 json
     * </p>
     *
     * @param object 对象
     * @return json 字符串
     * @author c332030
     */
    public static String toJson(@NonNull Object object) {
        return GSON.toJson(object);
    }

    /**
     * <p>
     * Description: json 转 对象
     * </p>
     *
     * @param json json 字符串
     * @param tClass 类型
     * @param <T> 实体类
     * @return 实体对象
     * @author c332030
     */
    public static <T> T fromJson(@NonNull String json, @NonNull Class<T> tClass) {
        return GSON.fromJson(json, tClass);
    }

    /**
     * <p>
     * Description: json 转 List
     * </p>
     *
     * @param json json 字符串
     * @param tClass 类型
     * @param <T> 实体类
     * @return 实体集合
     * @author c332030
     */
    public static <T> List<T> fromJsonOfArray(@NonNull String json, @NonNull Class<T> tClass) {
        Type type = new TypeToken<ArrayList<T>>(){}.getType();
        return GSON.fromJson(json, type);
    }
}
