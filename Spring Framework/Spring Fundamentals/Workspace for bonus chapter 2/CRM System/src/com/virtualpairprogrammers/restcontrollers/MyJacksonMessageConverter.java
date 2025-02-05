package com.virtualpairprogrammers.restcontrollers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class MyJacksonMessageConverter extends ObjectMapper
{
	public MyJacksonMessageConverter()
	{
		configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	}
}
