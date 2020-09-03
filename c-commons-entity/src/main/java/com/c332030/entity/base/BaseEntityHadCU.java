package com.c332030.entity.base;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * <p>
 * Description: BaseEntityHadCU
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
@Data
public abstract class BaseEntityHadCU<E> extends BaseEntityHadCreateTime<E> {
    private static final long serialVersionUID = 9222290124901358197L;

    /**
     * 创建人 id
     */
    private Long createId;

    /**
     * 创建人名
     */
    private String createName;

    /**
     * 最后更新人 id
     */
    private Long lastUpdateId;

    /**
     * 最后更新人名
     */
    private String lastUpdateName;

    /**
     * 最后更新人时间
     */
    private LocalDateTime lastUpdateTime;
}
