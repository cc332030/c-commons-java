package com.c332030.util.io;

import java.io.File;

import org.apache.commons.lang3.StringUtils;

import lombok.NonNull;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * Description: Files
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
@Slf4j
public class Files {

    /**
     * <p>
     * Description: 获取文件信息
     * </p>
     *
     * @param file 文件
     * @return 文件信息对象
     * @author c332030
     */
    public static FileNameInfo nameOfFile(@NonNull File file) {

        val fileName = file.getName();
        val pointIndex = fileName.lastIndexOf('.');

        var pureName = fileName;
        var extension = StringUtils.EMPTY;
        if(pointIndex != -1) {

            pureName = fileName.substring(0, pointIndex);
            extension = fileName.substring(pointIndex + 1);
        }

        return new FileNameInfo(fileName, file.getParent(), fileName, pureName, extension);
    }

    public static FileNameInfo nameOfFile(@NonNull String name) {

        var pathIndex = name.lastIndexOf('/');
        if(-1 == pathIndex) {
            pathIndex = name.lastIndexOf('\\');
        }

        var path = StringUtils.EMPTY;

        var fileName = name;
        if(-1 != pathIndex) {
            fileName = name.substring(pathIndex + 1);
            path = name.substring(0, pathIndex);
        }

        var pureName = fileName;
        var extension = StringUtils.EMPTY;

        val pointIndex = fileName.lastIndexOf('.');
        if(-1 != pointIndex) {
            pureName = fileName.substring(0, pointIndex);
            extension = fileName.substring(pointIndex + 1);
        }

        return new FileNameInfo(name, path, fileName, pureName, extension);
    }

}
