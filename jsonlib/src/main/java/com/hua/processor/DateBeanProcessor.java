/**
  * @filename DateBeanProcessor.java
  * @description  
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.processor;

import java.util.Calendar;
import java.util.Date;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonBeanProcessor;

 /**
 * @type DateBeanProcessor
 * @description  
 * @author qianye.zheng
 */
public final class DateBeanProcessor implements JsonBeanProcessor {

	/**
	 * @description 
	 * @param bean
	 * @param jsonConfig
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public JSONObject processBean(Object bean, JsonConfig jsonConfig) {
		/**
		 * 支持 java.sql.Date java.util.Date
		 */
	      JSONObject jsonObject = null;
	      if( bean instanceof java.sql.Date ){
	    	  // 转成 java.util.Date类型
	         bean = new Date( ((java.sql.Date) bean).getTime() );
	      }
	      if( bean instanceof Date ){
	         Calendar c = Calendar.getInstance();
	         c.setTime( (Date) bean );
	         jsonObject = new JSONObject().element( "year", c.get( Calendar.YEAR ) )
	               .element( "month", c.get( Calendar.MONTH ) )
	               .element( "day", c.get( Calendar.DAY_OF_MONTH ) )
	               .element( "hours", c.get( Calendar.HOUR_OF_DAY ) )
	               .element( "minutes", c.get( Calendar.MINUTE ) )
	               .element( "seconds", c.get( Calendar.SECOND ) )
	               .element( "milliseconds", c.get( Calendar.MILLISECOND ) );
	      }else{
	         jsonObject = new JSONObject( true );
	      }
	      return jsonObject;
	}

}
