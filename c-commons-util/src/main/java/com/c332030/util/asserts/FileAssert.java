package com.c332030.util.asserts;

import java.io.File;

import javax.annotation.Nullable;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * <p>
 * Description: FileAssert
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileAssert {

    /**
     * <p>
     * Description: 存在则报错
     * </p>
     *
     * @param file 文件
     * @param message 错误信息
     * @author c332030
     */
    public static void exists(@Nullable File file, String message) {
        CAssert.notNull(file, message);
        CAssert.state(!file.exists(), message);
    }

    /**
     * <p>
     * Description: 不存在则报错
     * </p>
     *
     * @param file 文件
     * @param message 错误信息
     * @author c332030
     */
    public static void notExists(@Nullable File file, String message) {
        CAssert.notNull(file, message);
        CAssert.state(file.exists(), message);
    }

    /**
     * <p>
     * Description: 不为文件则报错
     * </p>
     *
     * @param file 文件
     * @param message 错误信息
     * @author c332030
     */
    public static void notFile(@Nullable File file, String message) {
        CAssert.notNull(file, message);
        CAssert.state(!file.isDirectory(), message);
    }

    /**
     * <p>
     * Description: 不为文件夹则报错
     * </p>
     *
     * @param file 文件
     * @param message 错误信息
     * @author c332030
     */
    public static void notDirectory(@Nullable File file, String message) {
        CAssert.notNull(file, message);
        notExists(file, message);
        CAssert.state(file.isDirectory(), message);
    }
}
