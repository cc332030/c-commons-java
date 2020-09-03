package com.c332030.constant.enumerable.sys;

import com.c332030.constant.enumerable.annotation.GenerateDataDict;
import com.c332030.constant.enumerable.data.IDataDictEnum;

/**
 * <p>
 * Description: YesNoEnum
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
@GenerateDataDict
public enum YesNoEnum implements IDataDictEnum {

    YES("是")

    , NO("否")
    ;

    /**
     * 描述
     */
    private final String text;

    YesNoEnum(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }
}
