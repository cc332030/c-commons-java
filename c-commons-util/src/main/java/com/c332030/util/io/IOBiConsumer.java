package com.c332030.util.io;

import java.io.IOException;
import java.util.function.BiConsumer;

/**
 * <p>
 * Description: IOBiConsumer 不使用 BiConsumer 的原因是避免装箱
 * </p>
 *
 * @author c332030
 * @version 1.0
 * @see BiConsumer
 */
@FunctionalInterface
public interface IOBiConsumer {

    void accept(byte[] bytes, int length) throws IOException;

}
