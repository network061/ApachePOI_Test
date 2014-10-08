package com.yping.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {

	
	public static String now(){
		return df.format(new Date()).toString();
	}
	static DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
}
