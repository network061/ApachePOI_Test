package com.yping.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Settings {

	public Settings(String fileName){
		FileInputStream file = null;
		try {
			file = new FileInputStream(fileName);
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
