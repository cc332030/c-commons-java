package com.c332030.util.io;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.annotation.Nonnull;

import com.c332030.constant.io.SizeUnitEnum;
import com.c332030.model.io.Size;

import static com.c332030.constant.io.ByteConstants.BYTES_OF_MB;

/**
 * <p>
 * Description: IOUtils
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
public abstract class IOUtils extends org.apache.commons.io.IOUtils {

    private static final int DEFAULT_SIZE = BYTES_OF_MB;

    /**
     * <p>
     * Description: read string from input stream
     * </p>
     *
     * @param inputStream 输入流
     * @return 流中数据
     * @throws IOException IO异常
     * @author c332030
     */
    public static String toString(@Nonnull InputStream inputStream) throws IOException {
        return toString(inputStream, StandardCharsets.UTF_8);
    }

    /**
     * <p>
     * Description: 读取数据
     * </p>
     *
     * @param inputStream 输入流
     * @param consumer 数据处理
     * @throws IOException IO异常
     * @author c332030
     */
    public static void read(@Nonnull InputStream inputStream, @Nonnull IOBiConsumer consumer) throws IOException {
        read(inputStream, consumer, DEFAULT_SIZE);
    }

    /**
     * <p>
     * Description: 读取数据
     * </p>
     *
     * @param inputStream 输入流
     * @param consumer 数据处理
     * @param size 每次大小
     * @throws IOException IO异常
     * @author c332030
     */
    public static void read(@Nonnull InputStream inputStream, @Nonnull IOBiConsumer consumer, int size) throws IOException {

        int length;
        byte[] bytes = new byte[size];
        while ((length = inputStream.read(bytes)) > 0) {
            consumer.accept(bytes, length);
            if(length < size) {
                break;
            }
        }
    }

    private static final SizeUnitEnum[] SIZE_UNITS = SizeUnitEnum.values();

    /**
     * <p>
     * Description: get size from byte length
     * </p>
     *
     * @param length byte length
     * @return the suit size with unit of byte length
     * @author c332030
     */
    public static Size byteToSize(long length) {

        int index = 0;

        long result;
        while ((result = length / 1024) > 0) {
            index++;
            length = result;
        }

        return new Size(length, SIZE_UNITS[index]);
    }
}
