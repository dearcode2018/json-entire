/**
  * @filename CommaValue.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.hua.serializer.SplitIntValueDeserializer;

import lombok.Data;

/**
 * @type CommaValue
 * @description 
 * @author qianye.zheng
 */
@Data
public final class CommaValue {
    
    @JsonDeserialize(using = SplitIntValueDeserializer.class)
    private CommaSplitIntValue splitValue;
    
    private String name;
    
}
