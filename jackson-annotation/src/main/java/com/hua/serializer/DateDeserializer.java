/**
  * @filename DateDeserializer.java
  * @description 
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.serializer;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.hua.constant.FormatConstant;

 /**
 * @type DateDeserializer
 * @description 日期 反序列化器
 * @author qye.zheng
 */
public final class DateDeserializer extends JsonDeserializer<Date> {

	private static final DateFormat format = new SimpleDateFormat(FormatConstant.DATE_FORMAT_yyyy_MM_dd);
	
	/**
	 * @description 
	 * @param p
	 * @param ctxt
	 * @return
	 * @throws IOException
	 * @throws JsonProcessingException
	 * @author qye.zheng
	 */
	@Override
	public Date deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		String value = p.getValueAsString();
		Date date = null;
		try {
			date = format.parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return date;
	}

	
}
