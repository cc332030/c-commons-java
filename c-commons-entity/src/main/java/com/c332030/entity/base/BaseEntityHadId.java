package com.c332030.entity.base;

import lombok.Data;
import lombok.ToString;

/**
 * <p>
 * Description: BaseEntityHadId
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
@Data
@ToString(callSuper = false)
public class BaseEntityHadId<E> extends BaseEntity {
    private static final long serialVersionUID = -8720518184648057614L;

    /**
     * 主键
     */
    private E id;
}
