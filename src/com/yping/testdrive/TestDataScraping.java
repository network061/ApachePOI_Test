package com.yping.testdrive;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

import com.yping.classes.DataScraping;
import com.yping.util.Files;

public class TestDataScraping {

	public static void main(String[] args) {
		
		Files data = new Files();
		String logPath = null;     //初始化DataScraping类的日志参数
		DataScraping dataScraping = null;
		String argu_year = null;
		String argu_yearAndMonth = null;
		String argu_path = null;
		for(File aTxt:data.listDataFiles()){
			
			logPath = data.getLogPath()+"DataScrapingLogger.xml";
			dataScraping = new DataScraping(aTxt,logPath);
			try {
				argu_year = aTxt.getName().substring(0,aTxt.getName().indexOf("年"));
				argu_yearAndMonth = aTxt.getName().substring(0,aTxt.getName().indexOf("月")).replace("年","-");
				argu_path = data.getResultPath(argu_year).concat(aTxt.getName());
				dataScraping.readData(new PrintWriter(argu_path),argu_yearAndMonth+"-");
			} catch (FileNotFoundException e) {
				// TODO test.txt创建失败
				e.printStackTrace();
			}
		}	
	}
}
