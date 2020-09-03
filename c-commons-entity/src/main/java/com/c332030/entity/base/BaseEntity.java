package com.c332030.entity.base;

import lombok.Data;

import com.c332030.entity.IEntity;

/**
 * <p>
 * Description: BaseEntity
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
@Data
public abstract class BaseEntity implements IEntity {
    private static final long serialVersionUID = 465302895944694735L;
}
