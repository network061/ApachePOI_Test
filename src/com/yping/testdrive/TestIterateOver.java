package com.yping.testdrive;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import com.yping.classes.XLSExtractor;
import com.yping.util.Files;

public class TestIterateOver {

	/**
	 * 测试IteraterOver类,将xls文件数据读取为txt文档
	 * @param args
	 */
	public static void main(String[] args) {
		
		Files files = new Files();
		
		FileInputStream fileIn = null; //根据xlsPath参数构造FileInputStream对象
		String logPath = null;		   //IterateOver类构造方法参数
		PrintWriter output = null;     //数据输出文件路径,IterateOver对象writeData方法参数
		XLSExtractor iterate = null;  	
		int[] colsIndex = null;
		File[] fileArray = new File(files.getXlsPath()).listFiles();
		for(File aFile:fileArray){
			try {
				fileIn = new FileInputStream(aFile.getPath());
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			logPath = files.getLogPath().concat(aFile.getName().replace("xls","log"));
			System.out.println(logPath);
			try {
				output = new PrintWriter(files.getXlsDatasPath().concat(aFile.getName().replace("xls","txt")));
			} catch (IOException e) {
				// TODO 如果指定文件存在，但它是一个目录，而不是一个常规文件；或者该文件不存在，但无法创建它；抑或因为其他某些原因而无法打开它
				e.printStackTrace();
			}
	
			colsIndex = new int[] {1,2,3,4,5,6,8,12,13};
			iterate = new XLSExtractor(fileIn,colsIndex,logPath);
			iterate.output(output); 
		}			
	}
}
