package com.yping.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * ScanFile类提供scan方法扫描整个文档每行字串,并将这些字串以数组返回。
 * @author 楊平
 * 
 */
public class ScanFile {
	
  public ScanFile(File aFile) {
	 DOMConfigurator.configure("./loggerConfig/ScanFileLogger.xml");
	 this.aFile = aFile;
	 in = null;
  }
  public String[] scan(){
	  if(aFile != null){
		lines = new LinkedList<String>();
		try {
			in = new Scanner(new FileReader(aFile));
			while(in.hasNextLine()){
				lines.add(in.nextLine());
			}
		} catch (FileNotFoundException e) {
			logger.info(Time.now()+"-"+aFile.getPath()+"　读取时抛出异常:FileNotFoundException.");
			e.printStackTrace();
		}   
	  }else{
		  return new String[]{""};
	  }
	  return transToArr();
  }
  public String[] transToArr(){
	  if(!lines.isEmpty()){
		  results = new String[lines.size()];
		  lines.toArray(results);
		  return results;
	  }
	  return new String[]{""};
  }
  Scanner in ;
  File aFile;
  LinkedList<String> lines;
  String[] results;
  static Logger logger =Logger.getLogger("com.yping.util.ScanFile");
}
