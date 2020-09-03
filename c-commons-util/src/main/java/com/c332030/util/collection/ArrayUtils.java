package com.c332030.util.collection;

import com.c332030.constant.enumerable.sys.EqualEnum;
import com.c332030.util.Tools;

/**
 * <p>
 * Description: ArrayUtils
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
public abstract class ArrayUtils extends org.apache.commons.lang3.ArrayUtils {

    /**
     * <p>
     * Description: 数组比较
     * </p>
     *
     * @param objects1 数组 1
     * @param objects2 数组 2
     * @param equalEnums 相等的附加条件 Set
     * @return boolean
     * @author c332030
     */
    public static boolean equals(Object[] objects1, Object[] objects2, EqualEnum... equalEnums) {

        if(objects1 == objects2) {
            return true;
        }

        int mark = EqualEnum.of(equalEnums);

        boolean nullEqualEmpty = EqualEnum.isEnable(mark, EqualEnum.NULL_EQUAL_EMPTY);

        if(null == objects1) {
            return nullEqualEmpty && 0 == objects2.length;
        }

        if(null == objects2) {
            return nullEqualEmpty && 0 == objects1.length;
        }

        int length = objects1.length;
        if(length != objects2.length) {
            return false;
        }

        for(int i = 0; i < length; i++) {
            if(! Tools.equals(objects1[i], objects2[i], mark)) {
                return false;
            }
        }

        return true;
    }
}
