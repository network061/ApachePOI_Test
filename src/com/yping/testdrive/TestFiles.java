package com.yping.testdrive;

import java.io.File;

import com.yping.util.Files;

public class TestFiles {

	/**
	 * ����Files���еķ���
	 * @param args
	 */
	public static void main(String[] args) {
	  File subDir = new File("./results");
	  for(String year:subDir.list()){
		  File files = new File(subDir.getPath().concat(File.separator+ year));
		  for(File file:files.listFiles()){
			  System.out.println(file.getName());
		  }
	  }	}
	
}
