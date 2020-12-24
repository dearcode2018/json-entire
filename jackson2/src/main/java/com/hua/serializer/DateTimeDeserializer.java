/**
  * @filename DateTimeDeserializer.java
  * @description 
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.serializer;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

 /**
 * @type DateTimeDeserializer
 * @description 日期-时间 反序列化器
 * @author qye.zheng
 */
public final class DateTimeDeserializer extends JsonDeserializer<Date> {

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
		return null;
	}

	
}
