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
public class BaseEntityHadLongIdAndUpdateTime extends BaseEntityHadLongIdAndCreateTime {

    private static final long serialVersionUID = -5668865570352209301L;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
}
