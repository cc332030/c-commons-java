package com.c332030.util.collection;

import java.util.Map;

import javax.annotation.Nonnull;

import org.apache.commons.collections4.MapUtils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * <p>
 * Description: CMapUtils
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CMapUtils {

    /**
     * <p>
     * Description: 取不到值时取默认值
     * </p>
     *
     * @param map map
     * @param key 键
     * @param defaultV 默认值
     * @param <K> key generics
     * @param <V> value generics
     * @return map value
     * @author c332030（袁兴旺）
     */
    public static <K, V> V get(@Nonnull Map<K, V> map, @Nonnull K key, @Nonnull V defaultV) {
        if(MapUtils.isEmpty(map)) {
            return null;
        }
        return map.getOrDefault(key, defaultV);
    }

    /**
     * <p>
     * Description: 取不到值时取 key
     * </p>
     *
     * @param map map
     * @param key 键
     * @param <K> key generics
     * @return map value
     * @author c332030（袁兴旺）
     */
    public static <K> K getDefaultKey(@Nonnull Map<K, K> map, @Nonnull K key) {
        return get(map, key, key);
    }
}
