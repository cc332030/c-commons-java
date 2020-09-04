package com.c332030.constant.sys.io;

/**
 * <p>
 * Description: ByteConstans
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
public abstract class ByteConstants {

    /**
     * 1 kb 字节数
     */
    public static final Integer BYTES_OF_KB = 1024;

    /**
     * 1 mb 字节数
     */
    public static final Integer BYTES_OF_MB = BYTES_OF_KB * 1024;

    /**
     * 1 gb 字节数
     */
    public static final Integer BYTES_OF_GB = BYTES_OF_MB * 1024;

    /**
     * 1 tb 字节数
     */
    public static final Long BYTES_OF_TB = BYTES_OF_GB * 1024L;

    /**
     * 1 pb 字节数
     */
    public static final Long BYTES_OF_PB = BYTES_OF_TB * 1024;

    /**
     * 1 eb 字节数
     */
    public static final Long BYTES_OF_EB = BYTES_OF_PB * 1024;

}
