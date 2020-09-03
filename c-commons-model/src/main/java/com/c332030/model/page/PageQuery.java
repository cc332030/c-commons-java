package com.c332030.model.page;

import lombok.Data;

/**
 * <p>
 * Description: QueryPage 先实现，不知道会不会用
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
@Data
public class PageQuery implements IPage {

    /**
     * 当前页
     */
    private Integer pageNum;

    /**
     * 页大小
     */
    private Integer pageSize;

    /**
     * 排序
     */
    private String orderBy;
}
