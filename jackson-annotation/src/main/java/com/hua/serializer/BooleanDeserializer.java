/**
  * @filename BooleanDeserializer.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.hua.constant.YesNo;


/**
 * @type BooleanDeserializer
 * @description 布尔类型 反序列化
 * @author qianye.zheng
 */
public class BooleanDeserializer extends JsonDeserializer<Boolean> {
    
    /**
     * @description 
     * @param p
     * @param ctxt
     * @return
     * @throws IOException
     * @throws JsonProcessingException
     * @author qianye.zheng
     */
    @Override
    public Boolean deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        final String value = p.getValueAsString();
        if (YesNo.isYes(value)) {
            return Boolean.TRUE;
        }
        
        return Boolean.FALSE;
    }
    
}
