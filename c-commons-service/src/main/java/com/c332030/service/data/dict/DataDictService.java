package com.c332030.service.data.dict;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import org.reflections.Reflections;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import lombok.Data;
import lombok.NonNull;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

import com.c332030.constant.PackageConstants;
import com.c332030.constant.enums.annotation.GenerateDataDict;
import com.c332030.constant.enums.data.IDataDictEnum;
import com.c332030.model.sys.KeyValue;
import com.c332030.service.CAbstractService;
import com.c332030.util.data.JsonUtils;

/**
 * <p>
 * Description: DataDictServiceImpl
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
@Slf4j
public class DataDictService extends CAbstractService {

    String DATA_BASE_PACKAGE = PackageConstants.BASE;

    /**
     * 数据字典map
     */
    private static final Map<String, DataDict> DATA_DICT_MAP = new ConcurrentHashMap<>();

    DataDictService() {

        var reflections = new Reflections(DATA_BASE_PACKAGE);
        var dataDictClassSet = reflections.getTypesAnnotatedWith(GenerateDataDict.class);
        dataDictClassSet.forEach(clazz -> {

            log.debug("Init IDataDictEnum {}", clazz.getName());

            if(! IDataDictEnum.class.isAssignableFrom(clazz)) {
                log.error("{} not implements IDataDictEnum", clazz.getName());
                return;
            }

            @SuppressWarnings("unchecked")
            var dataDict = (Class<IDataDictEnum>)clazz;
            add(dataDict);
        });

        log.info("Initialized");
    }

    public static String getDataDictName(@NonNull Class<IDataDictEnum> dataDictClass) {
        return dataDictClass.getSimpleName();
    }

    public static void add(Class<IDataDictEnum> dataDictClass) {
        val dataDictName = getDataDictName(dataDictClass);
        DATA_DICT_MAP.put(dataDictName, new DataDict(dataDictClass));
    }

    @Data
    public static class DataDict {

        public DataDict(Class<?> tClass) {
            this.tClass = tClass;

            var enums = (Enum<?>[])tClass.getEnumConstants();

            var dataDictBuilder = new ImmutableMap.Builder<String, IDataDictEnum>();
            var keyValuesBuilder = new ImmutableList.Builder<KeyValue>();

            for(var enumObj: enums) {

                var dataDictEnum = (IDataDictEnum)enumObj;
                var name = enumObj.name();
                var text = dataDictEnum.getText();

                dataDictBuilder.put(name, dataDictEnum);
                keyValuesBuilder.add(new KeyValue(name, text));
            }

            dataDictMap = dataDictBuilder.build();
            keyValues = keyValuesBuilder.build();

            keyValueJson = JsonUtils.toJson(keyValues);
        }

        /**
         * 类型
         */
        private final Class<?> tClass;

        /**
         * json 字符串
         */
        private final Map<String, IDataDictEnum> dataDictMap;

        /**
         * 键值对
         */
        private final List<KeyValue> keyValues;

        /**
         * json 字符串
         */
        private final String keyValueJson;
    }

    public DataDict getDataDict(String enumClassName) {
        return MapUtils.getObject(DATA_DICT_MAP, enumClassName);
    }

    public String getValue(Class<IDataDictEnum> dataDictClass, String key) {
        return getValue(getDataDictName(dataDictClass), key);
    }

    public String getValue(String dataDictName, String key) {

        var dataDict = getDataDict(dataDictName);
        if(null == dataDict) {
            return StringUtils.EMPTY;
        }

        var dataDictEnum = MapUtils.getObject(dataDict.dataDictMap, key);
        return dataDictEnum == null ? StringUtils.EMPTY : dataDictEnum.getText();
    }
}
