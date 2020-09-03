package com.c332030.entity.base;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * <p>
 * Description: BaseEntityHadCreateTime
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
@Data
public class BaseEntityHadStringIdAndCreateTime extends BaseEntityHadId<String> {

    private static final long serialVersionUID = -5668865570352209301L;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
