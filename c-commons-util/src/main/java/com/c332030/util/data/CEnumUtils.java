package com.c332030.util.data;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * <p>
 * Description: CEnumUtils
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CEnumUtils {

    public static <K, V extends Enum<V>> Map<K, V> toMap(Class<V> vClass, Function<V, K> kFunction) {

        var map = new HashMap<K, V>();
        for (V enumConstant : vClass.getEnumConstants()) {
            var previous = map.put(kFunction.apply(enumConstant), enumConstant);
            if(null != previous) {
                throw new IllegalStateException("had previous value: " + enumConstant);
            }
        }
        return Collections.unmodifiableMap(map);
    }

    public static <K, V extends Enum<V>> V mapGet(Map<K, V> map, K key) {
        Objects.requireNonNull(key, "key can't be null");
        return Objects.requireNonNull(map.get(key), () -> "no enum with key: " + key);
    }

}
