/**
  * @filename DateMorpherEx.java
  * @description  
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.processor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import net.sf.ezmorph.MorphException;
import net.sf.ezmorph.object.AbstractObjectMorpher;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

 /**
 * @type DateMorpherEx
 * @description  日期时间类型解析 (反序列化)
 * @author qianye.zheng
 */
public final class DateMorpherEx extends AbstractObjectMorpher {

	private Date defaultValue;
    private String[] formats;
    private boolean lenient;
    private Locale locale;

    /**
     * 
     * @description 构造方法
     * @param formats
     * @author qianye.zheng
     */
    public DateMorpherEx(String[] formats)
    {
      this(formats, Locale.getDefault(), false);
    }

    /**
     * 
     * @description 构造方法
     * @param formats
     * @param lenient
     * @author qianye.zheng
     */
    public DateMorpherEx(String[] formats, boolean lenient)
    {
      this(formats, Locale.getDefault(), lenient);
    }

    /**
     * 
     * @description 构造方法
     * @param formats
     * @param defaultValue
     * @author qianye.zheng
     */
    public DateMorpherEx(String[] formats, Date defaultValue)
    {
      this(formats, defaultValue, Locale.getDefault(), false);
    }

    /**
     * 
     * @description 构造方法
     * @param formats
     * @param defaultValue
     * @param locale
     * @param lenient
     * @author qianye.zheng
     */
    public DateMorpherEx(String[] formats, Date defaultValue, Locale locale, boolean lenient)
    {
      super(true);
      if ((formats == null) || (formats.length == 0)) {
        throw new MorphException("invalid array of formats");
      }

      this.formats = formats;

      if (locale == null)
        this.locale = Locale.getDefault();
      else {
        this.locale = locale;
      }

      this.lenient = lenient;
      setDefaultValue(defaultValue);
    }

    /**
     * 
     * @description 构造方法
     * @param formats
     * @param locale
     * @author qianye.zheng
     */
    public DateMorpherEx(String[] formats, Locale locale)
    {
      this(formats, locale, false);
    }

    /**
     * 
     * @description 构造方法
     * @param formats
     * @param locale
     * @param lenient
     * @author qianye.zheng
     */
    public DateMorpherEx(String[] formats, Locale locale, boolean lenient)
    {
      if ((formats == null) || (formats.length == 0)) {
        throw new MorphException("invalid array of formats");
      }

      this.formats = formats;

      if (locale == null)
        this.locale = Locale.getDefault();
      else {
        this.locale = locale;
      }

      this.lenient = lenient;
    }

    /**
     * 
     * @description 
     * @param obj
     * @return
     * @author qianye.zheng
     */
    @Override
    public boolean equals(Object obj)
    {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }

      if (!(obj instanceof DateMorpherEx)) {
        return false;
      }

      DateMorpherEx other = (DateMorpherEx)obj;
      EqualsBuilder builder = new EqualsBuilder();
      builder.append(this.formats, other.formats);
      builder.append(this.locale, other.locale);
      builder.append(this.lenient, other.lenient);
      if ((super.isUseDefault()) && (other.isUseDefault())) {
        builder.append(getDefaultValue(), other.getDefaultValue());
        return builder.isEquals();
      }if ((!super.isUseDefault()) && (!other.isUseDefault())) {
        return builder.isEquals();
      }
      return false;
    }

    /**
     * 
     * @description 
     * @return
     * @author qianye.zheng
     */
   public Date getDefaultValue()
   {
       if(this.defaultValue!=null)
           return (Date)this.defaultValue.clone();
       else
           return this.defaultValue;
   }

   /**
    * 
    * @description 
    * @return
    * @author qianye.zheng
    */
   @Override
   public int hashCode()
   {
     HashCodeBuilder builder = new HashCodeBuilder();
     builder.append(this.formats);
     builder.append(this.locale);
     builder.append(this.lenient);
     if (super.isUseDefault()) {
       builder.append(getDefaultValue());
     }
     return builder.toHashCode();
   }

   /**
    * 
    * @description 
    * @param value
    * @return
    * @author qianye.zheng
    */
   @Override
   public Object morph(Object value)
   {
     if (value == null) {
       return null;
     }

     if (Date.class.isAssignableFrom(value.getClass())) {
       return (Date)value;
     }

     if (!supports(value.getClass())) {
       throw new MorphException(value.getClass() + " is not supported");
     }

     String strValue = (String)value;
     SimpleDateFormat dateParser = null;

     for (int i = 0; i < this.formats.length; ++i) {
       if (dateParser == null)
         dateParser = new SimpleDateFormat(this.formats[i], this.locale);
       else {
         dateParser.applyPattern(this.formats[i]);
       }
       dateParser.setLenient(this.lenient);
       try {
         return dateParser.parse(strValue.toLowerCase());
       }
       catch (ParseException localParseException)
       {
       }

     }

     if (super.isUseDefault()) {
       return this.defaultValue;
     }
     throw new MorphException("Unable to parse the date " + value);
   }

   /**
    * 
    * @description 
    * @return
    * @author qianye.zheng
    */
   @Override
   public Class<?> morphsTo()
   {
     return Date.class;
   }

   /**
    * 
    * @description 
    * @param defaultValue
    * @author qianye.zheng
    */
   public void setDefaultValue(Date defaultValue)
   {
       if(defaultValue!=null)
           this.defaultValue = ((Date)defaultValue.clone());
       else
           this.defaultValue = null;
   }

   /**
    * 
    * @description 
    * @param clazz
    * @return
    * @author qianye.zheng
    */
   @SuppressWarnings({"rawtypes"})
   @Override
   public boolean supports(Class clazz)
   {
     return String.class.isAssignableFrom(clazz);
   }
	 

}
