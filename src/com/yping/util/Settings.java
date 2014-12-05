package com.yping.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class Settings {

	public Settings(String fileName){
		InputStreamReader file = null;
		try {
			file = new InputStreamReader(new FileInputStream(fileName), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    setting = new Properties();
		try {
			setting.load(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getValue(String key){
		if(null != setting){
			return setting.getProperty(key);
		}
		return null;
	}
	Properties setting;
}
