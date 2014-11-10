
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
 * 对ApachePOI生成的数据进行处理,例如:过滤重复TDCS记录
 * @author 杨平
 * update 20140830 存储每条记录的数组对象,最后一个为行ID:yymmdd+lineIndex
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
			int  indexOfYear = fileName.indexOf("年");
		    year = fileName.substring(0,indexOfYear);
			month = fileName.substring(indexOfYear+1,fileName.indexOf("月"));
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
				logger.info("初始化文件输出对象异常:FileNotFoundException。");
				logger.info("输出文件路径:"+destination);
				e.printStackTrace();
			}
		}
	}
	File src;
	HashSet<String> newRecords;//不良信息数据txt文档过滤重复记录后的集合
	String year;
	String month;
	Time time;
	ScanFile scan;
	static Logger logger =Logger.getLogger("com.yping.classes.DataScraping");
}
