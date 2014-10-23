
package com.yping.classes;

import java.io.File;
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
	
	public DataScraping(File aFile,String logPath ){
		DOMConfigurator.configure(logPath);
		logger.info("[INFO]"+Time.now());
		scan = new ScanFile(aFile);
		newRecords = new HashSet<String>();
	}
	
	public void readData(PrintWriter output, String argu_yearAndMonth){
		String[] tokens = null;
		int lineIndex = 0;
		String[] lines = scan.scan();
		for(String line:lines){
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
	HashSet<String> newRecords;//�����ظ���¼��ļ���
	Time time;
	ScanFile scan;
	static Logger logger =Logger.getLogger("com.yping.classes.DataScraping");
}
