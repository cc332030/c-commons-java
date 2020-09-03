package com.c332030.util.asserts;

import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * Description: CAssert
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
@Slf4j
public abstract class Assert {
    /**
     * <p>
     * Description: 不允许为空
     * </p>
     *
     * @param object 对象
     * @author c332030
     */
    public static void notNull(@Nullable Object object) {
        notNull(object, "object can't be null");
    }

    /**
     * <p>
     * Description: 不允许为空
     * </p>
     *
     * @param object 对象
     * @param message 错误信息
     * @author c332030
     */
    public static void notNull(@Nullable Object object, @Nonnull String message) {
        if(null == object) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * <p>
     * Description: 字符串不允许为空
     * </p>
     *
     * @param string 字符串
     * @author c332030
     */
    public static void notEmpty(@Nullable String string) {
        notEmpty(string, "string can't be empty");
    }

    /**
     * <p>
     * Description: 字符串不允许为空
     * </p>
     *
     * @param string 字符串
     * @param message 异常信息
     * @author c332030
     */
    public static void notEmpty(@Nullable String string, @Nonnull String message) {
        Objects.requireNonNull(string, message);
        if(string.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * <p>
     * Description: state 必须为 true
     * </p>
     *
     * @param result 结果
     * @author c332030
     */
    public static void state(boolean result) {
        state(result, "The state must be true");
    }

    /**
     * <p>
     * Description: state 必须为 true
     * </p>
     *
     * @param result 结果
     * @param message 错误信息
     * @author c332030
     */
    public static void state(boolean result, @Nonnull String message) {
        if(!result) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * <p>
     * Description: 值 必须为 true
     * </p>
     *
     * @param bool 值
     * @author c332030
     */
    public static void isTrue(boolean bool) {
        isTrue(bool, "this expression must be true");
    }

    /**
     * <p>
     * Description: 值 必须为 true
     * </p>
     *
     * @param bool 值
     * @param message 错误信息
     * @author c332030
     */
    public static void isTrue(boolean bool, @Nonnull String message) {
        if(!bool) {
            throw new IllegalArgumentException(message);
        }
    }

}
