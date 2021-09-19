package com.c332030.constant.enums;

/**
 * <p>
 * Description: IEnum
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
public interface IEnum {

    /**
     * <p>
     * Description: 获取描述
     * </p>
     *
     * @return 枚举描述
     * @author c332030
     */
    default String getText(){
        return "";
    }
}
