package com.c332030.model.sys;

/**
 * <p>
 * Description: IEmpty
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
public interface IEmpty {

    /**
     * <p>
     * Description: 判断是否为空
     * </p>
     *
     * @return 判断是否为空结果
     * @author c332030
     */
    default boolean isEmpty() {
        return false;
    }

}
