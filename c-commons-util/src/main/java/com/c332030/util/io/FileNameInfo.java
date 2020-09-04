package com.c332030.util.io;

import lombok.Value;

/**
 * <p>
 * Description: FileNameInfo
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
@Value
public class FileNameInfo {

    // /home/images.tar.gz

    /**
     * 原始名
     * /home/images.tar.gz
     */
    String originName;

    /**
     * 文件路径
     * /home
     */
    String path;

    /**
     * 文件名，有扩展名
     * images.tar.gz
     */
    String name;

    /**
     * 纯文件名，无扩展名
     * images.tar
     */
    String pureName;

    /**
     * 扩展名
     * gz
     */
    String extension;
}
