package com.yping.web;

import java.io.File;

public class testString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String one = "2013��1�·ݲ�����Ϣ";
		String two = "11";
		System.out.println(one.substring(one.indexOf("��")+1,one.indexOf("��")));
		System.out.println(two.length());
		String dataDir = "." + File.separator + "results" + File.separator+"2013"+File.separator;
		System.out.println(dataDir);
		File[] files = new File(dataDir).listFiles();
		if(null != files){
			System.out.println(files.length);
		}
		System.out.println(one.substring(0,one.indexOf("��")));
	}

}
