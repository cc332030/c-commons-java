package com.c332030.constant.enumerable.sys;

/**
 * <p>
 * Description: EqualEnum
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
public enum EqualEnum {

    /**
     * 只要有一个为 null 则相等
     */
    NULL_EQUAL,

    /**
     * 只要有一个为 空 则相等
     */
    EMPTY_EQUAL,

    /**
     * null 等价于 空，如 null 和 length = 0
     */
    NULL_EQUAL_EMPTY,

    /**
     * 存在继承关系就相等
     */
    INSTANCE_EQUAL,

    /**
     * 左边的类实现或继承右边的接口或类
     */
    INSTANCE_EQUAL_LEFT,

    /**
     * 右边的类实现或继承左边的接口或类
     */
    INSTANCE_EQUAL_RIGHT,
    ;

    private final int mark = 1 << this.ordinal();

    public int getMark() {
        return mark;
    }

    public static boolean isEnable(int marks, EqualEnum equalEnum) {
        return null != equalEnum && (marks & equalEnum.mark) != 0;
    }

    public static int of(EqualEnum... equalEnums) {

        if(null == equalEnums || equalEnums.length == 0) {
            return 0;
        }

        int value = 0;
        for (EqualEnum equalEnum : equalEnums) {
            value |= equalEnum.getMark();
        }

        return value;
    }
}
