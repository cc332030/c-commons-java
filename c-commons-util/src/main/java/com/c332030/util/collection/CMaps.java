package com.c332030.util.collection;

import java.util.LinkedHashMap;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * <p>
 * Description: Maps
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CMaps {

    /**
     * <p>
     * Description: 创建链表map
     * </p>
     *
     * @param size 初始大小
     * @param <K> 键
     * @param <V> 值
     * @return 链表map
     * @author c332030
     */
    public static <K, V> LinkedHashMap<K, V> newLinkedHashMap(int size) {
        return new LinkedHashMap<>(size);
    }

}
