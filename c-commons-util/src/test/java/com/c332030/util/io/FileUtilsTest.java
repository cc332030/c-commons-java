package com.c332030.util.io;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import org.junit.Test;

import lombok.SneakyThrows;
import lombok.val;

/**
 * <p>
 * Description: FileUtilsTest
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
public class FileUtilsTest {

    @Test
    @SneakyThrows
    public void testSplit() {
        val path = "d:\\file\\";

        val file = new File(path + "Lavender.jpg");
        val childPath = path + "child\\";
        val childPathFile = new File(childPath);

        FileUtils.deleteDirectory(childPathFile);
        if(!childPathFile.mkdirs()) {
            throw new IOException("创建文件夹失败");
        }

        val files = FileUtils.split(
            file
            , (name, index) -> new File(
                childPath + name + '.'
                    + StringUtils.leftPad(String.valueOf(index), 3, '0')
            )
        );

        System.out.println(files);
    }

    @Test
    @SneakyThrows
    public void testMerge() {
        val path = "d:\\file\\";
        FileUtils.merge(
            new File(path + "child").listFiles()
            , new File(path + "Lavender-new.jpg")
        );
    }

}
