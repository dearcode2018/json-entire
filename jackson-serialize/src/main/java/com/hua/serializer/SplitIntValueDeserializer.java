/**
  * @filename SplitIntValueDeserializer.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.serializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.hua.bean.CommaSplitIntValue;
import com.hua.util.StringUtil;

/**
 * @type SplitIntValueDeserializer
 * @description 拆分器序列化
 * @author qianye.zheng
 */
public class SplitIntValueDeserializer extends JsonDeserializer<CommaSplitIntValue> {
    
    private static final String SPLIT_CHAR = ",";
    
    /**
     * @description 
     * @param p
     * @param ctxt
     * @return
     * @throws IOException
     * @author qianye.zheng
     */
    @Override
    public CommaSplitIntValue deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        final CommaSplitIntValue result = new CommaSplitIntValue();
        result.setOrginal(p.getValueAsString());
        if (StringUtil.isEmpty(result.getOrginal())) { // 返回空集合
            result.setValues(Collections.emptyList());
            return result;
        }
        final String[] valueArr = result.getOrginal().split(SPLIT_CHAR);
        final List<Integer> list = new ArrayList<>(valueArr.length);
        for (String e : valueArr) {
            list.add(Integer.valueOf(e.trim()));
        }
        result.setValues(list);
        
        return result;
    }
    
}
