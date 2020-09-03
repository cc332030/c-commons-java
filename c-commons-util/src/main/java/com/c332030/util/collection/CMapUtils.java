package com.c332030.util.collection;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.annotation.Nonnull;

import org.apache.commons.collections4.MapUtils;

import com.c332030.util.concurrent.CConcurrentUtils;

/**
 * <p>
 * Description: CMapUtils
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
public abstract class CMapUtils {

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

        V v = map.get(key);
        if(null != v) {
            return v;
        }

        return defaultV;
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

    /**
     * <p>
     * Description: 取不到值时取默认值，并 put 进 map
     * </p>
     *
     * @param map map
     * @param key 键
     * @param supplier 获取默认值
     * @param <K> key generics
     * @param <V> value generics
     * @return map value
     * @author c332030（袁兴旺）
     */
    public static <K, V> V getWithPut(
        @Nonnull Map<K, V> map,
        @Nonnull K key,
        @Nonnull Supplier<V> supplier
    ) {
        V value = map.get(key);
        if(null == value) {
            V defaultV = supplier.get();
            map.put(key, defaultV);
            return defaultV;
        }

        return value;
    }

    /**
     * <p>
     * Description: 取不到值时取默认值，并 put 进 map，并发方法
     * </p>
     *
     * @param map map
     * @param key 键
     * @param supplier 获取默认值
     * @param <K> key generics
     * @param <V> value generics
     * @return map value
     * @author c332030
     */
    public static <K, V> V getWithPutSync(
        @Nonnull Map<K, V> map,
        @Nonnull K key,
        @Nonnull Supplier<V> supplier
    ) {
        return CConcurrentUtils.getWithSet(
            map
            , () -> map.get(key)
            , supplier
            , (V v) -> map.put(key, v)
        );
    }

    /**
     * <p>
     * Description: 取不到值时取默认值，并 put 进 map
     * </p>
     *
     * @param map map
     * @param key 键
     * @param function 获取默认值
     * @param <K> key generics
     * @param <V> value generics
     * @return map value
     * @author c332030（袁兴旺）
     */
    public static <K, V> V getWithPut(
        @Nonnull Map<K, V> map,
        @Nonnull K key,
        @Nonnull Function<K, V> function
    ) {
        V value = map.get(key);
        if(null == value) {
            V defaultV = function.apply(key);
            map.put(key, defaultV);
            return defaultV;
        }

        return value;
    }

    /**
     * <p>
     * Description: 取不到值时取默认值，并 put 进 map，并发方法
     * </p>
     *
     * @param map map
     * @param key 键
     * @param function 获取默认值
     * @param <K> key generics
     * @param <V> value generics
     * @return map value
     * @author c332030
     */
    public static <K, V> V getWithPutSync(
        @Nonnull Map<K, V> map,
        @Nonnull K key,
        @Nonnull Function<K, V> function
    ) {
        return CConcurrentUtils.getWithSet(
            map
            , () -> map.get(key)
            , () -> function.apply(key)
            , (V v) -> map.put(key, v)
        );
    }
}
