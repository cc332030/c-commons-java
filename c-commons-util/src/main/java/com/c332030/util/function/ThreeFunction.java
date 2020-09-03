package com.c332030.util.function;

/**
 * <p>
 * Description: ThreeFunction give three param and return one result
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
@FunctionalInterface
public interface ThreeFunction <P1, P2, P3, R> {

    /**
     * <p>
     * Description: Applies this function to the given argument.
     * </p>
     *
     * @param p1 param 1
     * @param p2 param 2
     * @param p3 param 3
     * @return 返回值
     * @author c332030
     */
    R apply(P1 p1, P2 p2, P3 p3);

}
