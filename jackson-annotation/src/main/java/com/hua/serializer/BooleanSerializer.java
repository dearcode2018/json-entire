/**
  * @filename BooleanSerializer.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.hua.constant.YesNo;


/**
 * @type BooleanSerializer
 * @description 布尔类型 序列化
 * @author qianye.zheng
 */
public class BooleanSerializer extends JsonSerializer<Boolean> {
    
    /**
     * @description 
     * @param value
     * @param gen
     * @param serializers
     * @throws IOException
     * @author qianye.zheng
     */
    @Override
    public void serialize(Boolean value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (Boolean.TRUE.equals(value)) {
            gen.writeString(YesNo.YES.getCode());
        } else {
            gen.writeString(YesNo.NO.getCode());
        }
    }
    
}
