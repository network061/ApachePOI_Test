
package com.yping.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashSet;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.yping.util.ScanFile;
import com.yping.util.Time;
/**
 * ��ApachePOI���ɵ����ݽ��д���,����:�����ظ�TDCS��¼
 * @author ��ƽ
 * update 20140830 �洢ÿ����¼���������,���һ��Ϊ��ID:yymmdd+lineIndex
 */
public class DataScraping {
	
	public DataScraping(File src,String logPath ){
		DOMConfigurator.configure(logPath);
		logger.info("[INFO]"+Time.now());
		this.src = src;
		newRecords = new HashSet<String>();
		setTimeField();
	}
	public void setTimeField(){
		if(src!=null){
			String fileName =src.getName();
			int  indexOfYear = fileName.indexOf("��");
		    year = fileName.substring(0,indexOfYear);
			month = fileName.substring(indexOfYear+1,fileName.indexOf("��"));
		}
	}
	public void output(String destination){
		if(src!=null){
			scan = new ScanFile(src);
			String[] tokens = null;
			int lineIndex = 0;
			String[] lines = scan.scan();
			destination = destination.concat(year+File.separator+src.getName());
			PrintWriter output = null;
			try {
				output = new PrintWriter(destination);
				for(String line:lines){
					tokens = line.split("\\|");
					if(tokens.length > 3 && !tokens[tokens.length-1].equals(" ") ){
						if(newRecords.add(tokens[3])){
							output.println(line.concat(year+"-"+month+"-" + (++lineIndex)));
							logger.info("Line ID:" + year+"-"+month+"-"+lineIndex + "  Line Array Length:" + (tokens.length+1));
						}
					}		
				}
				output.flush();
				output.close();	
			} catch (FileNotFoundException e) {
				logger.info("��ʼ���ļ���������쳣:FileNotFoundException��");
				logger.info("����ļ�·��:"+destination);
				e.printStackTrace();
			}
		}
	}
	File src;
	HashSet<String> newRecords;//������Ϣ����txt�ĵ������ظ���¼��ļ���
	String year;
	String month;
	Time time;
	ScanFile scan;
	static Logger logger =Logger.getLogger("com.yping.classes.DataScraping");
}
