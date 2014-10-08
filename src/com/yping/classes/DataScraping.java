
package com.yping.classes;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.yping.util.Time;
/**
 * ��ApachePOI���ɵ����ݽ��д���,����:�����ظ�TDCS��¼
 * @author ��ƽ
 * update 20140830 �洢ÿ����¼���������,���һ��Ϊ��ID:yymmdd+lineIndex
 */
public class DataScraping {

	public DataScraping(Scanner in,String logPath ){
		DOMConfigurator.configure(logPath);
		logger.info("[INFO]"+Time.now());
		this.in = in;
		newRecords = new HashSet<String>();

	}
	
	public void readData(PrintWriter output, String argu_yearAndMonth){
		String line = null;
		String[] tokens = null;
		int lineIndex = 0;
		while(in.hasNextLine()){
			line = in.nextLine();
			tokens = line.split("\\|");
			if(tokens.length > 3 && !tokens[tokens.length-1].equals(" ") ){
				if(newRecords.add(tokens[3])){
					output.println(line.concat(argu_yearAndMonth + (++lineIndex)));
					logger.info("Line ID:" + argu_yearAndMonth+lineIndex + "  Line Array Length:" + (tokens.length+1));
				}
			}	
		}
		
		output.flush();
		output.close();		
	}
	
	Scanner in;
	HashSet<String> newRecords;//�����ظ���¼��ļ���
	Time time;
	static Logger logger =Logger.getLogger("com.yping.classes.DataScraping");
}
