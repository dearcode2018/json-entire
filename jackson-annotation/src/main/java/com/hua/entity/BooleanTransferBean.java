/**
  * @filename BooleanTransferBean.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hua.serializer.BooleanDeserializer;
import com.hua.serializer.BooleanSerializer;

import lombok.Data;

/**
 * @type BooleanTransferBean
 * @description 布尔类型转换器
 * @author qianye.zheng
 */
@Data
public class BooleanTransferBean {

    /**
     * 使用 布尔 序列化和反序列化器
     */
    
    @JsonSerialize(using = BooleanSerializer.class)
    @JsonDeserialize(using = BooleanDeserializer.class)
    private boolean today;
    
    @JsonSerialize(using = BooleanSerializer.class)
    @JsonDeserialize(using = BooleanDeserializer.class)
    private Boolean tomorrow;
    
    public BooleanTransferBean() {}
}
