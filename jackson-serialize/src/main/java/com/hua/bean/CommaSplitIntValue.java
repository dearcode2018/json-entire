/**
  * @filename CommaSplitIntValue.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.bean;

import java.util.List;

import lombok.Data;

/**
 * @type CommaSplitIntValue
 * @description 英文逗号隔开的拆分值
 * 字符串、数值
 * @author qianye.zheng
 */
@Data
public final class CommaSplitIntValue {
    
    /* 原始值 */
    private String orginal;
    
    /* 拆分之后的数值 */
    private List<Integer> values;
    
}
