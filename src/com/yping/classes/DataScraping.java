
package com.yping.classes;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashSet;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.yping.util.ScanFile;
import com.yping.util.Time;
/**
 * 对ApachePOI生成的数据进行处理,例如:过滤重复TDCS记录
 * @author 杨平
 * update 20140830 存储每条记录的数组对象,最后一个为行ID:yymmdd+lineIndex
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
	HashSet<String> newRecords;//过滤重复记录后的集合
	Time time;
	ScanFile scan;
	static Logger logger =Logger.getLogger("com.yping.classes.DataScraping");
}
