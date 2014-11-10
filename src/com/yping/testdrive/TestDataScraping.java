package com.yping.testdrive;

import java.io.File;
import com.yping.classes.DataScraping;
import com.yping.util.Files;

public class TestDataScraping {

	public static void main(String[] args) {
		
		Files data = new Files();
		String logPath = null;     //初始化DataScraping类的日志参数
		DataScraping dataScraping = null;
		
		for(File aTxt:data.listDataFiles()){
			logPath = data.getLogPath()+"DataScrapingLogger.xml";
			dataScraping = new DataScraping(aTxt,logPath);
			dataScraping.output(data.getResultPath());
		}	
	}
}
