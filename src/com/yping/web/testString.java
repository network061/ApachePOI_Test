package com.yping.web;

import java.io.File;

public class testString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String one = "2013年1月份不良信息";
		String two = "11";
		System.out.println(one.substring(one.indexOf("年")+1,one.indexOf("月")));
		System.out.println(two.length());
		String dataDir = "." + File.separator + "results" + File.separator+"2013"+File.separator;
		System.out.println(dataDir);
		File[] files = new File(dataDir).listFiles();
		if(null != files){
			System.out.println(files.length);
		}
		System.out.println(one.substring(0,one.indexOf("年")));
	}

}
