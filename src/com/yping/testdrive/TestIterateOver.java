package com.yping.testdrive;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import com.yping.classes.IterateOver;
import com.yping.util.Files;

public class TestIterateOver {

	/**
	 * ����IteraterOver��,��xls�ļ����ݶ�ȡΪtxt�ĵ�
	 * @param args
	 */
	public static void main(String[] args) {
		
		Files files = new Files();
		
		FileInputStream fileIn = null; //����xlsPath��������FileInputStream����
		String logPath = null;		   //IterateOver�๹�췽������
		PrintWriter output = null;     //��������ļ�·��,IterateOver����writeData��������
		IterateOver iterate = null;  	
		int[] colsIndex = null;
	
		for(File aFile:files.listXlsFiles()){
			try {
				fileIn = new FileInputStream(aFile.getPath());
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			logPath = files.getLogPath().concat(aFile.getName().replace("xls","log"));
			System.out.println(logPath);
			try {
				output = new PrintWriter(files.getDatasPath().concat(aFile.getName().replace("xls","txt")));
			} catch (IOException e) {
				// TODO ���ָ���ļ����ڣ�������һ��Ŀ¼��������һ�������ļ������߸��ļ������ڣ����޷����������ֻ���Ϊ����ĳЩԭ����޷�����
				e.printStackTrace();
			}
	
			colsIndex = new int[] {1,2,3,4,5,6,8,12,13};
			iterate = new IterateOver(fileIn,colsIndex,logPath);
			iterate.writeData(output); 
		}			
	}
}
