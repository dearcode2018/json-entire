/**
  * @filename FullBean.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.bean;

import java.time.LocalDateTime;
import java.util.List;

import com.hua.annotation.FieldConvert;

import lombok.Data;

/**
 * @type FullBean
 * @description 
 * @author qianye.zheng
 */
@Data
public class FullBean {
    
    @FieldConvert(aliase = "15x", bool = true, bools = {"Y", "N"}, strategy = FieldConvert.Strategy.ALIASE)
    private boolean size15x;
    
    @FieldConvert(aliase = "20x", bool = true, bools = {"Y", "N"}, strategy = FieldConvert.Strategy.ALIASE)
    private boolean size20x;
    
    @FieldConvert(dateTimeFormat = FieldConvert.DateTimeFormat.DATE_TIME)
    private LocalDateTime dateTime;
    
    private int type;
    
    @FieldConvert(aliase = "isVip", bool = true, bools = {"Y", "N"}, strategy = FieldConvert.Strategy.ALIASE)
    private boolean vip;
    
    /* 驼峰的方式 */
    @FieldConvert
    private int myBr;
    
    /* 不使用转换器，直接转换 */
    private String state;
    
    private List<String> images;
    
    @FieldConvert(aliase = "luping", bool = true, bools = {"1", "0"}, strategy = FieldConvert.Strategy.ALIASE)
    private boolean luPing;
    
    @FieldConvert
    private String outBr;
    
    @FieldConvert(aliase = "isAudio", bool = true, bools = {"1", "0"}, strategy = FieldConvert.Strategy.ALIASE)
    private boolean audio;
    
    @FieldConvert(aliase = "autoplay", bool = true, bools = {"1", "0"}, strategy = FieldConvert.Strategy.ALIASE)
    private boolean autoPlay;
    
    private String duration;
    
    @FieldConvert(aliase = "playerid",strategy = FieldConvert.Strategy.ALIASE)
    private String playerId;
    
    @FieldConvert(strategy = FieldConvert.Strategy.AGGREGATE, prefix = "15x_link")
    private List<String> link15xs;
    
    @FieldConvert(strategy = FieldConvert.Strategy.AGGREGATE, prefix = "mp4_link")
    private List<String> mp4Links;
    
    @FieldConvert(strategy = FieldConvert.Strategy.AGGREGATE, prefix = "swf_link")
    private List<String> swfLinks;
    
    @FieldConvert(aliase = "keepsource", bool = true, bools = {"1", "0"}, strategy = FieldConvert.Strategy.ALIASE)
    private boolean keepSource;
    
    @FieldConvert
    private String firstImage;
    
    @FieldConvert(aliase = "md5checksum")
    private String md5CheckSum;
    
    @FieldConvert
    private int originalAb;
    
    @FieldConvert(strategy = FieldConvert.Strategy.AGGREGATE, prefix = "level_group_")
    private List<String> levelGroups;
    
    @FieldConvert(strategy = FieldConvert.Strategy.AGGREGATE, prefix = "level_status_")
    private List<Integer> levelStatus;
    
    @FieldConvert
    private int originalTotFr;
    
    private int previewDuration;
    
    @FieldConvert(aliase = "level_group_1_15x", strategy = FieldConvert.Strategy.ALIASE)
    private String levelGroupOne15x;
    
    @FieldConvert(aliase = "level_group_2_15x", strategy = FieldConvert.Strategy.ALIASE)
    private String levelGroupTwo15x;
    
    /* 对象类型 */
    @FieldConvert(aliase = "one_object", strategy = FieldConvert.Strategy.OBJECT)
    private OneObject oneObject;
    
    /* 对象列表类型 */
    @FieldConvert(strategy = FieldConvert.Strategy.OBJECTS)
    private List<OneObject> oneObjects;
    
    /**
     * 
     * @type OneObject
     * @description 
     * @author qianye.zheng
     */
    @Data
    public static final class OneObject {
        
        @FieldConvert
        private long sourceFilesize;
        
        @FieldConvert(aliase = "15x", bool = true, strategy = FieldConvert.Strategy.ALIASE)
        private boolean size15x;
        
        private int type;
        
        /* 驼峰的方式 */
        @FieldConvert
        private int myBr;
        
        private List<String> images;
    }
    
    
}
