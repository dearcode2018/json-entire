/**
  * @filename FieldConvert.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.format.DateTimeFormatter;

/**
 * @type FieldConvert
 * @description 字段转换
 * @author qianye.zheng
 */
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldConvert {
    
    /* 策略，默认: 驼峰 */
    Strategy strategy() default Strategy.CAMLE;
    
    /* 别名 策略为ALIASE 设置  */
    String aliase() default "";
    
    /* 前缀，AGGREGATE 设置 字段的前缀 */
    String prefix() default "";
    
    /* 策略为 AGGREGATE 设置，起始索引 */
    int start() default 1;
    
    /* 是否是布尔值，布尔值使用 YesNo进行转换 */
    boolean bool() default false;
    
    /* 布尔的枚举，序列化时用到，[0]对应true，[1]对应false */
    String[] bools() default {};
    
    /* 时间格式*/
    DateTimeFormat dateTimeFormat() default DateTimeFormat.NONE;
    
    /**
     * 
     * @type Strategy
     * @description 转换策略
     * @author qianye.zheng
     */
    enum Strategy {
        /* 驼峰 */
        CAMLE,
        
        /* 别名 */
        ALIASE,
        
        /* 聚合 */
        AGGREGATE,
        
        /* 对象 */
        OBJECT,
        
        /* 对象列表 */
        OBJECTS,
        
        ;
    }
    
    /**
     * 
     * @type DateTimeFormat
     * @description 
     * @author qianye.zheng
     */
    enum DateTimeFormat {
        
        NONE(null),
        
        TIME(DateTimeFormatter.ofPattern("HH:mm:ss")),
        
        DATE(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
        
        DATE_TIME(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
        
        ;
        
        private DateTimeFormatter formatter;

        /**
         * @description 构造方法
         * @param formatter
         * @author qianye.zheng
         */
        private DateTimeFormat(DateTimeFormatter formatter) {
            this.formatter = formatter;
        }

        /**
        * @return the formatter
        */
        public final DateTimeFormatter getFormatter() {
            return formatter;
        }
        
    }
    
}
