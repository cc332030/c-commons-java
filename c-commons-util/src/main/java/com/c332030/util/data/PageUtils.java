package com.c332030.util.data;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;

import org.apache.commons.collections4.CollectionUtils;

import lombok.val;

import com.c332030.model.page.IPage;
import com.c332030.model.page.PageQuery;

/**
 * <p>
 * Description: PageUtils
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
public class PageUtils {

    private static final int DEFAULT_PAGE_SIZE = 1000;

    private PageUtils() {}

    /**
     * <p>
     * Description: do operator for every page
     * </p>
     *
     * @param function function to get rows
     * @param consumer consumer to deal row
     * @param <E> the row type
     * @return total
     * @author c332030
     */
    public static <E> long doEach(
        Function<IPage, Collection<E>> function,
        Consumer<E> consumer
    ) {
        return doEach(DEFAULT_PAGE_SIZE, function, consumer);
    }

    /**
     * <p>
     * Description: do operator for every page
     * </p>
     *
     * @param size page size
     * @param function function to get rows
     * @param consumer consumer to deal row
     * @param <E> the row type
     * @return total
     * @author c332030
     */
    public static <E> long doEach(
        int size,
        Function<IPage, Collection<E>> function,
        Consumer<E> consumer
    ) {
        return doOperator(size, function, rows -> rows.forEach(consumer));
    }

    /**
     * <p>
     * Description: do operator for every page
     * </p>
     *
     * @param function function to get rows
     * @param consumer consumer to deal rows
     * @param <E> the row type
     * @return total
     * @author c332030
     */
    public static <E> long doOperator(
        Function<IPage, Collection<E>> function,
        Consumer<Collection<E>> consumer
    ) {
        return doOperator(DEFAULT_PAGE_SIZE, function, consumer);
    }

    /**
     * <p>
     * Description: do operator for every page
     * </p>
     *
     * @param size page size
     * @param function function to get rows
     * @param consumer consumer to deal rows
     * @param <E> the row type
     * @return total
     * @author c332030
     */
    public static <E> long doOperator(
        int size,
        Function<IPage, Collection<E>> function,
        Consumer<Collection<E>> consumer
    ) {

        val pageQuery = new PageQuery();
        pageQuery.setPageNum(1);
        pageQuery.setPageSize(size);

        int rowSize;
        long total = 0;

        Collection<E> rows;
        do {

            rows = function.apply(pageQuery);
            if(CollectionUtils.isEmpty(rows)) {
                break;
            }
            total += (rowSize = rows.size());
            pageQuery.setPageNum(pageQuery.getPageSize() + 1);

            consumer.accept(rows);
        } while (rowSize == size);

        return total;
    }

}
