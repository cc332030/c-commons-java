package com.c332030.util.concurrent;

import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.annotation.Nonnull;

/**
 * <p>
 * Description: ConcurrentUtils
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
public abstract class CConcurrentUtils {

    public static <E> E getWithSet(
        @Nonnull Object lockObject,
        @Nonnull Supplier<E> get,
        @Nonnull Supplier<E> init,
        @Nonnull Consumer<E> set
    ) {
        E e = get.get();
        if(null == e) {
            synchronized (lockObject) {
                e = get.get();
                if(null == e) {
                    e = init.get();
                    set.accept(e);
                }
            }
        }
        return e;
    }
}
