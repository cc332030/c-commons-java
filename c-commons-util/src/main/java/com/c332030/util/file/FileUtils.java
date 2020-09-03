package com.c332030.util.file;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

import org.apache.commons.lang3.StringUtils;

import lombok.Cleanup;
import lombok.NonNull;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

import com.c332030.util.asserts.Assert;
import com.c332030.util.asserts.FileAssert;
import com.c332030.util.asserts.MathAssert;

/**
 * <p>
 * Description: FileUtils
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
@Slf4j
public abstract class FileUtils extends org.apache.commons.io.FileUtils {

    /**
     * <p>
     * Description: 获取文件扩展名
     * </p>
     *
     * @param fileName 文件名
     * @return 扩展名
     * @author c332030
     */
    public static String getExtensionName(@NonNull String fileName) {

        int index = fileName.lastIndexOf('.');
        if(-1 == index) {
            return null;
        }

        return fileName.substring(index + 1);
    }

    /**
     * 默认读取字节数量
     */
    private static final int DEFAULT_BYTES_SIZE = 1024 * 1024;

    /**
     * <p>
     * Description: 确保文件夹存在
     * </p>
     *
     * @param file 文件夹
     * @throws IOException 异常
     * @author c332030
     */
    public static void makeSureDirectoryExists(@NonNull File file) throws IOException {

        if(file.exists()) {
            if(!file.isDirectory()) {
                throw new NotDirectoryException("not a directory");
            }
            return;
        }

        if(!file.mkdirs()) {
            throw new IOException("path: "
                + file.getPath()
                + " can't mkdir");
        }
    }

    /**
     * <p>
     * Description: 确保文件夹存在
     * </p>
     *
     * @param path 文件夹路径
     * @return 路径文件对象
     * @throws IOException 异常
     * @author c332030
     */
    public static File makeSureDirectoryExists(@NonNull String path) throws IOException {
        val file = new File(path);
        makeSureDirectoryExists(file);
        return file;
    }

    /**
     * <p>
     * Description: 获取文件数量
     * </p>
     *
     * @param totalSize 文件总大小
     * @param size 分片大小
     * @return 分割后文件数量
     * @author c332030
     */
    public static int getSplitFileNums(long totalSize, int size) {

        val fileNumLong = totalSize / size;
        MathAssert.overMaxInteger(fileNumLong, "over max file numbers, please set greater size");

        var fileNum = (int) fileNumLong;

        if (totalSize % size != 0) {
            fileNum++;
        }

        return fileNum;
    }

    /**
     * <p>
     * Description: 文件分割，直接字节分割
     * </p>
     *
     * @param file 需要分割的文件
     * @param size 片大小，单位：字节
     * @param getChildFile 生成子文件
     * @return 子文件集合
     * @throws IOException 异常
     * @author c332030
     */
    public static List<File> split(
        @NonNull final File file,
        final int size,
        @NonNull BiFunction<String, Integer, File> getChildFile
    ) throws IOException {

        Assert.state(size > 0, "size must greater then 0");

        FileAssert.notExists(file, "file not exists");
        FileAssert.notFile(file, "param file must be a file");

        val fileName = file.getName();
        val childFiles = new ArrayList<File>(getSplitFileNums(file.length(), size));

        val bytes = new byte[size];

        @Cleanup
        val inputStream = new BufferedInputStream(new FileInputStream(file));

        var index = 1;
        while (true) {
            val byteSize = inputStream.read(bytes);
            if(byteSize <= 0) {
                break;
            }

            val childFile = getChildFile.apply(fileName, index++);
            if (childFile.exists()) {
                throw new FileAlreadyExistsException("child file already exists");
            }

            childFiles.add(childFile);

            @Cleanup
            val outputStream = new BufferedOutputStream(new FileOutputStream(childFile));
            outputStream.write(bytes, 0, byteSize);
            outputStream.flush();

            if (byteSize < size) {
                break;
            }
        }

        return childFiles;
    }

    /**
     * <p>
     * Description: 文件分割，直接字节分割
     * </p>
     *
     * @param file 需要分割的文件
     * @param getChildFile 生成子文件
     * @return 子文件集合
     * @throws IOException 异常
     * @author c332030
     * @see FileUtils#split(File, int, BiFunction)
     */
    public static List<File> split(
        @NonNull final File file,
        @NonNull BiFunction<String, Integer, File> getChildFile
    ) throws IOException {
        return split(file, DEFAULT_BYTES_SIZE, getChildFile);
    }

    /**
     * <p>
     * Description: 文件分割，直接字节分割
     * </p>
     *
     * @param file 需要分割的文件
     * @param path 子文件路径
     * @return 子文件集合
     * @throws IOException 异常
     * @author c332030
     * @see FileUtils#split(File, BiFunction)
     */
    public static List<File> split(
        @NonNull final File file,
        @NonNull final String path
    ) throws IOException {
        return split(
            file
            , (name, index) -> new File(
                path + name
                    + StringUtils.leftPad(String.valueOf(index), 3, '0')
            )
        );
    }

    /**
     * <p>
     * Description: 文件分割，直接字节分割
     * </p>
     *
     * @param file 需要分割的文件
     * @return 子文件集合
     * @throws IOException 异常
     * @author c332030
     * @see FileUtils#split(File, BiFunction)
     */
    public static List<File> split(
        @NonNull final File file
    ) throws IOException {
        return split(file, file.getParent() + "/");
    }

    /**
     * <p>
     * Description: 文件合并，直接字节拼接
     * </p>
     *
     * @param files 子文件集合，需要事先排序
     * @param file 文件
     * @throws IOException 异常
     * @author c332030
     */
    public static void merge(
        @NonNull List<File> files,
        @NonNull File file
    ) throws IOException {

        FileAssert.exists(file, "target file already exists");

        val size = DEFAULT_BYTES_SIZE + 1;

        byte[] bytes = new byte[size];
        var byteSize = 0;

        @Cleanup
        val outputStream = new BufferedOutputStream(new FileOutputStream(file));
        for(val childFile: files) {

            @Cleanup
            val inputStream = new BufferedInputStream(new FileInputStream(childFile));
            while (true) {
                byteSize = inputStream.read(bytes);
                if(byteSize <= 0) {
                    break;
                }

                outputStream.write(bytes, 0, byteSize);
                if(byteSize < size) {
                    break;
                }
            }

            outputStream.flush();
        }
    }

    /**
     * <p>
     * Description: 件合并，直接字节拼接
     * </p>
     *
     * @param files 子文件集合，需要事先排序
     * @param file 文件
     * @throws IOException 异常
     * @author c332030
     * @see FileUtils#merge(List, File)
     */
    public static void merge(
        @NonNull File[] files,
        @NonNull File file
    ) throws IOException {
        merge(Arrays.asList(files), file);
    }

}
