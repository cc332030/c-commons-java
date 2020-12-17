package com.c332030.model.io;

import lombok.AllArgsConstructor;
import lombok.Data;

import com.c332030.constant.io.SizeUnitEnum;

/**
 * <p>
 * Description: FileSize
 * </p>
 *
 * @author c332030（袁兴旺）
 * @version 1.0
 */
@Data
@AllArgsConstructor
public class Size {

    /**
     * 大小
     */
    private Long size;

    /**
     * 单位
     */
    private SizeUnitEnum unit;

}
