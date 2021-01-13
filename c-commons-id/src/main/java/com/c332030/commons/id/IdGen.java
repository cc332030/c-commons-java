package com.c332030.commons.id;

/**
 * <p>
 * Description: IDGen
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
public interface IdGen {

    /**
     * <p>
     * Description: init
     * </p>
     *
     * @return init result
     * @author c332030
     */
    default boolean init() {
        return true;
    }

    long get(int workerId);

}
