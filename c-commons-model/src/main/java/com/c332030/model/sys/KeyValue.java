package com.c332030.model.sys;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import com.c332030.model.IModel;

/**
 * <p>
 * Description: vo
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class KeyValue implements IModel {

    private static final long serialVersionUID = -817238873356071112L;

    /**
     * 键
     */
    @NonNull
    private String key;

    /**
     * 值
     */
    @NonNull
    private String value;
}
