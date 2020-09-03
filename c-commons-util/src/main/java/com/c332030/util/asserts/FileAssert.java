package com.c332030.util.asserts;

import java.io.File;

import javax.annotation.Nullable;

/**
 * <p>
 * Description: FileAssert
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
public abstract class FileAssert {

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
        Assert.notNull(file, message);
        Assert.state(!file.exists(), message);
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
        Assert.notNull(file, message);
        Assert.state(file.exists(), message);
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
        Assert.notNull(file, message);
        Assert.state(!file.isDirectory(), message);
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
        Assert.notNull(file, message);
        notExists(file, message);
        Assert.state(file.isDirectory(), message);
    }
}
