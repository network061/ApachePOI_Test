package com.yping.testdrive;

import java.io.File;

import com.yping.util.Files;

public class TestFiles {

	/**
	 * ����Files���еķ���
	 * @param args
	 */
	public static void main(String[] args) {
	  Files data = new Files();
	  File[] files = data.getAllFiles(new File(data.getReportsPath()));
	  for(File report:files){
		  System.out.println(report.getPath());
	  }
	}
	
}
