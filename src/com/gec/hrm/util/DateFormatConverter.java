package com.gec.hrm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class DateFormatConverter implements Converter<String, Date> {

	@Override
	public Date convert(String s) {
		Date date=null;
		try {
			date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s);
			return date;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
