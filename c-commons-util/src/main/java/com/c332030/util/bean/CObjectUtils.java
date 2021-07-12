package com.c332030.util.bean;

import com.c332030.constant.enumerable.sys.EqualEnum;
import com.c332030.model.sys.IEmpty;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * <p>
 * Description: Tools
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CObjectUtils {

    /**
     * <p>
     * Description: 判断是否为空
     * </p>
     *
     * @param empty 实体
     * @return 判断是否为空 结果
     * @author c332030
     */
    public static boolean isEmpty(IEmpty empty) {
        return null == empty || empty.isEmpty();
    }

    /**
     * <p>
     * Description: 判断是否不为空
     * </p>
     *
     * @param empty 实体
     * @return 判断是否不为空 结果
     * @author c332030
     */
    public static boolean isNotEmpty(IEmpty empty) {
        return null != empty && empty.isNotEmpty();
    }

    /**
     * <p>
     * Description: 对象比较
     * </p>
     *
     * @param object1 对象 1
     * @param object2 对象 2
     * @param equalEnums 标志
     * @return boolean
     * @author c332030
     */
    public static boolean equals(Object object1, Object object2, EqualEnum... equalEnums) {
        return equals(object1, object2, EqualEnum.of(equalEnums));
    }

    /**
     * <p>
     * Description: 对象比较
     * </p>
     *
     * @param object1 对象 1
     * @param object2 对象 2
     * @param mark 标志
     * @return boolean
     * @author c332030
     */
    public static boolean equals(Object object1, Object object2, int mark) {

        if(object1 == object2) {
            return true;
        }

        if(null == object1 || null == object2) {
            return mark != 0 && EqualEnum.isEnable(mark, EqualEnum.NULL_EQUAL);
        }

        if(object1.equals(object2)) {
            return true;
        }

        if(mark == 0) {
            return false;
        }

        boolean instanceEqual = EqualEnum.isEnable(mark, EqualEnum.INSTANCE_EQUAL);
        if(instanceEqual || EqualEnum.isEnable(mark, EqualEnum.INSTANCE_EQUAL_LEFT)) {
            if(object2.getClass().isInstance(object1)) {
                return true;
            }
        }

        if(instanceEqual || EqualEnum.isEnable(mark, EqualEnum.INSTANCE_EQUAL_RIGHT)) {
            return object1.getClass().isInstance(object2);
        }

        return false;
    }
}
