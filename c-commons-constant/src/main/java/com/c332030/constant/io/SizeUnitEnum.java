package com.c332030.constant.io;

/**
 * <p>
 * Description: SizeUnit
 * </p>
 *
 * @author c332030（袁兴旺）
 * @version 1.0
 */
public enum SizeUnitEnum {

    B,

    KB,

    MB,

    GB,

    TB,

    EB,

    PB,

    ;

    SizeUnitEnum() {}

    public String getCode() {
        return name();
    }

}
