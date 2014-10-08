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
		Scanner in = null;         //����DataScraping����readData��������һ
		String logPath = null;     //��ʼ��DataScraping�����־����
		DataScraping dataScraping = null;
		String argu_year = null;
		String argu_yearAndMonth = null;
		String argu_path = null;
		for(File aTxt:data.listDataFiles()){
			try {
				in = new Scanner(new FileReader(aTxt));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logPath = data.getLogPath()+"DataScrapingLogger.xml";
			dataScraping = new DataScraping(in,logPath);
			try {
				argu_year = aTxt.getName().substring(0,aTxt.getName().indexOf("��"));
				argu_yearAndMonth = aTxt.getName().substring(0,aTxt.getName().indexOf("��")).replace("��","-");
				argu_path = data.getResultPath(argu_year).concat(aTxt.getName());
				dataScraping.readData(new PrintWriter(argu_path),argu_yearAndMonth+"-");
			} catch (FileNotFoundException e) {
				// TODO test.txt����ʧ��
				e.printStackTrace();
			}
		}	
	}
}
