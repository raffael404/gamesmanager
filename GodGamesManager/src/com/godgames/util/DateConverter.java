package com.godgames.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {
	private static final SimpleDateFormat leConverter = new SimpleDateFormat("dd/MM/yyyy");
	private static final SimpleDateFormat beConverter = new SimpleDateFormat("yyyy-MM-dd");
	
	public static String date2String(Date date){
		return leConverter.format(date);
	}
	
	public static String bigEndian2LittleEndian(String date) throws ParseException {
		return leConverter.format(beConverter.parse(date));
	}
}
