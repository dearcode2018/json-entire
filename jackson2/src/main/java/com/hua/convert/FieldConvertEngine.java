/**
  * @filename FieldConvertEngine.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.convert;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Lists;
import com.hua.annotation.FieldConvert;
import com.hua.constant.YesNo;
import com.hua.util.EmptyUtil;
import com.hua.util.JacksonUtil;
import com.hua.util.StringUtil;

/**
 * @type FieldConvertEngine
 * @description 字段转换引擎
 * @author qianye.zheng
 */
@SuppressWarnings({"unchecked"})
public final class FieldConvertEngine {
    
    private FieldConvertEngine() {}
    
    /**
     * 
     * @description 序列化
     * @param <T>
     * @param clazz
     * @param target
     * @return
     * @author qianye.zheng
     */
    public static final <T> String serialize(final Class<T> clazz, final T target) {
        return JacksonUtil.writeAsString( getValue(clazz, target));
    }
    
    /**
     * 
     * @description 
     * @param clazz
     * @param target
     * @return
     * @author qianye.zheng
     */
    private static final Map<String, Object> getValue(final Class<?> clazz, final Object target) {
        final Field[] fields = clazz.getDeclaredFields();
        final Map<String, Object> map = new HashMap<>(fields.length);
        String name = null;
        for (Field field : fields) {
            try {
            field.setAccessible(true);
            final FieldConvert convert = field.getDeclaredAnnotation(FieldConvert.class);
            if (null == convert) { // 不使用转换器，直接设置
                map.put(field.getName(), field.get(target));
                continue;
            }
            name = StringUtil.isNotEmpty(convert.aliase()) ? convert.aliase() : field.getName();
            switch (convert.strategy()) {
                case CAMLE: // 驼峰
                    name = toUnderline(field.getName());
                    map.put(name, setValue(convert, field.get(target)));
                    break;
                    
                case ALIASE: // 别名
                    name = convert.aliase();
                    map.put(name, setValue(convert, field.get(target)));
                    break;
                    
                case AGGREGATE: // 聚合
                    final List<Object> list = (List<Object>) field.get(target);
                    int i = convert.start();
                    for (Object e : list) {
                        map.put(convert.prefix() + i, e);
                        i++;
                    }
                    break;
                    
                case OBJECT: // 对象
                    map.put(name, getValue(field.getType(), field.get(target)));
                    break;
                    
                case OBJECTS: // 对象列表
                    final List<Object> objs = (List<Object>) field.get(target);
                    if (EmptyUtil.isEmpty(objs)) {
                        continue;
                    }
                    final List<Object> resultObjs = Lists.newArrayListWithCapacity(objs.size());
                    map.put(name, resultObjs);
                    final Type type = field.getGenericType();
                    if (type instanceof ParameterizedType) {
                        final ParameterizedType parameterizedType = (ParameterizedType) type;
                        final Class<?> subClazz = Class.forName(parameterizedType.getActualTypeArguments()[0].getTypeName());
                        for (Object e : objs) {
                            resultObjs.add(getValue(subClazz, e));
                        }
                    }
                    break;
            }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return map;
    }
    
    
    /**
     * 
     * @description 
     * @param convert
     * @param value
     * @return
     * @author qianye.zheng
     */
    private static final Object setValue(final FieldConvert convert, final Object value) {
        if (convert.bool() && convert.bools().length > 0) {
            return Boolean.valueOf(value.toString()).booleanValue() ? convert.bools()[0] : convert.bools()[1];
        }
        return value;
    }
    
    /**
     * 
     * @description 反序列化
     * @param <T>
     * @param json
     * @param clazz
     * @return
     * @author qianye.zheng
     */
    public static final <T> T deserialize(final String json, final Class<T> clazz) {
        final Map<String, Object> map = JacksonUtil.readValue(json, Map.class);
        
        return build(clazz, map);
    }
    
    /**
     * 
     * @description 
     * @param clazz
     * @param map
     * @author qianye.zheng
     */
    private static final <T> T build(final Class<T> clazz, final Map<String, Object> map) {
        T instance = null;
        try {
            instance = clazz.newInstance();
            final Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                final FieldConvert convert = field.getDeclaredAnnotation(FieldConvert.class);
                field.setAccessible(true);
                if (null == convert) { // 不使用转换器，直接转换
                    field.set(instance, readValue(field.getType(), map.get(field.getName())));
                } else {
                    String name = null;
                    switch (convert.strategy()) {
                        case CAMLE: // 驼峰
                            setValue(convert, instance, map.get(toUnderline(field.getName())), field);
                            break;
                            
                        case ALIASE: // 别名
                            setValue(convert, instance, map.get(convert.aliase()), field);
                         break;
                         
                        case AGGREGATE: // 聚合
                            final List<Object> list = new ArrayList<>();
                            Object val = null;
                            while (null != (val = map.get(convert.prefix() + (convert.start() + list.size())))) {
                                list.add(val);
                            }
                            field.set(instance, list);
                            break;
                            
                        case OBJECT: // 对象
                            // 递归调用 优先使用注解标注的别名
                            name = StringUtil.isNotEmpty(convert.aliase()) ? convert.aliase() : field.getName();
                            final Object subObj = map.get(name);
                            if (null != subObj) { // 递归
                                field.set(instance, build(field.getType(), (Map<String, Object>) subObj));
                            }
                            break;
                            
                        case OBJECTS: // 对象列表
                            // 递归调用 优先使用注解标注的别名
                            name = StringUtil.isNotEmpty(convert.aliase()) ? convert.aliase() : field.getName();
                            final Object subObjs = map.get(name);
                            if (subObjs instanceof List) {
                                List<Object> objs = (List<Object>) field.get(instance);
                                final List<Map<String, Object>> mapObjs = (List<Map<String, Object>>) subObjs;
                                if (null == objs) {
                                    objs = new ArrayList<>(mapObjs.size());
                                }
                                final Type type = field.getGenericType();
                                if (type instanceof ParameterizedType) {
                                    final ParameterizedType parameterizedType = (ParameterizedType) type;
                                    final Class<?> subClazz = Class.forName(parameterizedType.getActualTypeArguments()[0].getTypeName());
                                    for (Map<String, Object> mapObj : mapObjs) {
                                        objs.add(build(subClazz, mapObj));
                                    }
                                }
                                field.set(instance, objs);
                            }
                            break;
                            
                            default:
                                break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        return instance;
    }
    
    /**
     * 
     * @description 
     * @param convert
     * @param instance
     * @param value
     * @param field
     * @author qianye.zheng
     */
    private static final void setValue(final FieldConvert convert, final Object instance, final Object value, final Field field) {
        try {
            if (convert.bool()) {
                field.set(instance, YesNo.isYes(value.toString()));
            } else if (FieldConvert.DateTimeFormat.NONE != convert.dateTimeFormat()) { // 日期时间类型
                switch (convert.dateTimeFormat()) {
                    case DATE:
                        field.set(instance, LocalDate.parse(value.toString(), convert.dateTimeFormat().getFormatter()));
                        break;
                    case TIME:
                        field.set(instance, LocalTime.parse(value.toString(), convert.dateTimeFormat().getFormatter()));
                        break;
                    case DATE_TIME:
                        field.set(instance, LocalDateTime.parse(value.toString(), convert.dateTimeFormat().getFormatter()));
                        break;            
                        
                        default:
                            break;
                }
            } else { // 其他类型
                field.set(instance, readValue(field.getType(), value));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 
     * @description 
     * @param type
     * @param value
     * @return
     * @author qianye.zheng
     */
    private static final Object readValue(final Class<?> type, final Object value) {
        if (type == int.class || type == Integer.class) {
            return Integer.parseInt(value.toString());
        } else if (type == long.class || type == Long.class) {
            return  Long.parseLong(value.toString());
        } else if (type == double.class || type == Double.class) {
            return Double.parseDouble(value.toString());
        }
        
        return value;
    }
    
    /**
     * 
     * @description 转成驼峰格式
     * @param underline 下划线间隔
     * @return
     * @author qianye.zheng
     */
    private static final String toCamel(final String underline) {
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, underline);
    }
    
    /**
     * 
     * @description 驼峰格式转成下划线格式
     * @param camel
     * @return
     * @author qianye.zheng
     */
    private static final String toUnderline(final String camel) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, camel);
    }
    
}
