package com.c332030.util.bean;

import java.lang.reflect.Constructor;
import java.util.*;

import org.apache.commons.lang3.ArrayUtils;

import com.google.common.collect.Maps;

import com.c332030.constant.enumerable.sys.EqualEnum;
import com.c332030.util.asserts.CAssert;
import com.c332030.util.collection.CArrayUtils;
import com.c332030.util.data.CStringUtils;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import net.sf.cglib.beans.BeanCopier;

/**
 * <p>
 * Description: BeanUtils
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CBeanUtils {

    /**
     * BeanCopier Map
     */
    private static final Map<String, Map<String, BeanCopier>> BEAN_COPIER_MAP = Maps.newConcurrentMap();

    /**
     * 创建 Bean 常用的等于附加条件
     */
    private static final int CREATE_BEAN_EQUAL_ENUM_MARK = EqualEnum.NULL_EQUAL.getMark() | EqualEnum.INSTANCE_EQUAL_RIGHT.getMark();

    /**
     * <p>
     * Description: 生成 BeanCopier
     * </p>
     *
     * @param cls 类名
     * @return org.springframework.cglib.beans.BeanCopier
     * @author c332030
     */
    public static Map<String, BeanCopier> getBeanCopierMap(Class<?> cls) {

        String key = CStringUtils.getKey(cls);

        Map<String, BeanCopier> beanCopierMap = BEAN_COPIER_MAP.get(key);

        if(null != beanCopierMap) {
            return beanCopierMap;
        }

        synchronized (BEAN_COPIER_MAP) {

            beanCopierMap = BEAN_COPIER_MAP.get(key);

            if(null != beanCopierMap) {
                return beanCopierMap;
            }

            beanCopierMap = Maps.newConcurrentMap();
            BEAN_COPIER_MAP.put(key, beanCopierMap);
        }

        return beanCopierMap;
    }

    /**
     * <p>
     * Description: 生成 BeanCopier
     * </p>
     *
     * @param cls1 类名 1
     * @param cls2 类名 2
     * @return org.springframework.cglib.beans.BeanCopier
     * @author c332030
     */
    public static BeanCopier getBeanCopier(Class<?> cls1, Class<?> cls2) {

        String key = CStringUtils.getKey(cls2);

        Map<String, BeanCopier> beanCopierMap = getBeanCopierMap(cls1);
        BeanCopier beanCopier = beanCopierMap.get(key);

        if(null != beanCopier) {
            return beanCopier;
        }

        synchronized (beanCopierMap) {

            beanCopier = beanCopierMap.get(key);

            if(null != beanCopier) {
                return beanCopier;
            }

            beanCopier = BeanCopier.create(cls1, cls2, false);
            beanCopierMap.put(key, beanCopier);
        }

        return beanCopier;
    }

    /**
     * <p>
     * Description: 复制属性
     * </p>
     *
     * @param from 来源实例
     * @param to 目标实例
     * @param <To> 目标类实例
     * @param <From> 数据类实例
     * @return Child
     * @author c332030
     */
    public static <To, From> To copyProperties(@NonNull From from, @NonNull To to) {

        getBeanCopier(from.getClass(), to.getClass()).copy(from, to, null);

        return to;
    }

    /**
     * <p>
     * Description: 复制属性
     * </p>
     *
     * @param from 数据来源实例
     * @param toClass 目标类
     * @param params 子类对象构造方法参数
     * @param <To> 目标类实例
     * @param <From> 数据类实例
     * @return Child
     * @author c332030
     */
    public static <To, From> To copyProperties(
        From from, Class<To> toClass, Object... params
    ) {
        return copyProperties(from, Objects.requireNonNull(newInstance(toClass, params)));
    }

    /**
     * <p>
     * Description: 创建类实例，允许参数为空
     * </p>
     *
     * @param eClass 类 class
     * @param params 构造方法参数
     * @param <E> 实例类
     * @return E
     * @author c332030
     */
    public static <E> E newInstance(Class<E> eClass, Object... params) {

        try {
            return getConstructor(eClass, params).newInstance(params);
        } catch (Exception e) {
            log.error("创建对象失败", e);
        }

        return null;
    }

    /**
     * <p>
     * Description: 遍历查找构造方法
     * </p>
     *
     * @param eClass 构造方法数组
     * @param paramClassArr 参数类型
     * @param <E> 实例类
     * @return 类构建方法
     * @throws Exception 构造方法查找异常
     * @author c332030
     */
    public static <E> Constructor<E> getConstructor(
        Class<E> eClass, Class<?>... paramClassArr
    ) throws Exception {

        CAssert.notNull(eClass, "类不存在");

        Constructor<?>[] constructors = eClass.getDeclaredConstructors();
        for(Constructor<?> constructor: constructors) {

            if(! CArrayUtils.equals(paramClassArr, constructor.getParameterTypes(), EqualEnum.NULL_EQUAL)) {
                continue;
            }

            @SuppressWarnings("unchecked") Constructor<E> eConstructor = (Constructor<E>)constructor;
            eConstructor.setAccessible(true);

            return eConstructor;
        }

        throw new NoSuchMethodException(eClass.getName() + ".<init>" + Arrays.toString(paramClassArr));
    }

    /**
     * <p>
     * Description: 获取构造方法
     * </p>
     *
     * @param eClass 类
     * @param params 参数
     * @param <E> 实例类
     * @return 类构建方法
     * @throws Exception 异常
     * @author c332030
     */
    public static <E> Constructor<E> getConstructor(
        Class<E> eClass, Object... params
    ) throws Exception {

        CAssert.notNull(eClass, "类不存在");

        Class<?>[] classes = null;

        if(ArrayUtils.isNotEmpty(params)) {

            int len = params.length;
            classes = new Class[len];
            for(int i = 0; i < len; i++) {
                Object object = params[i];
                classes[i] = null == object ? null : object.getClass();
            }
        }

        return getConstructor(eClass, classes);
    }

}
