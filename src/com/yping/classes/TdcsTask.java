package com.yping.classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.yping.util.ScanFile;

public class TdcsTask {

	public TdcsTask(String logConfigPath){
		DOMConfigurator.configure(logConfigPath);
	}
	
	/**
	 * 在所有文档docs中找出所有关键字keys
	 * @param keys
	 * @param docs
	 */
	public String[] doSearchRecord(String[] keys,File[] docs){
		HashSet<String> filter = new HashSet<String>();
		LinkedList<String> lines = new LinkedList<String>();
		Arrays.sort(docs);
		for(int i = docs.length -1; i >=0 ; --i){
			scan = new ScanFile(docs[i]);
			readStrings = scan.scan();
		    for(String line:readStrings){
		    	int counter =0;
				for(String key:keys){
					if(!line.contains(key)){
						break; 
					}
					++ counter;
				}
				if(counter == keys.length){
					if(filter.add(line.split("\\|")[3])){
						lines.add(line);
					}
				}
		    }
		}
		String[] result = new String[lines.size()];
		return lines.toArray(result);
	}
	public String[] doSearchReport(String[] keys,File[] datas){
		LinkedList<String> lines = new LinkedList<String>();
		String pdfText = "";
		StringBuilder strBuilder = new StringBuilder("");
		for(File datFile:datas){
			
			pdfText = getReport(datFile).getText();
			int counter =0;
			for(String key:keys){
				if(!pdfText.contains(key)){
					break; 
				}
				++ counter;
			}
			if(counter == keys.length){
				strBuilder.append(datFile.getName().replace(".dat",""));
				lines.add(datFile.getName().replace(".dat",""));
			}
		}
		String[] result = new String[lines.size()];
		return lines.toArray(result);
	}
	public Report getReport(File f){
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(f));
		} catch (FileNotFoundException e) {
			logger.info("new ObjectInputStream throw FileNotFoundException.",e);
		} catch (IOException e) {
			logger.info("new ObjectInputStream throw IOException.",e);
		}
		if(in != null){
			logger.info("create ObjectInputStream obj ["+f.getName()+"] successed.");
			try {
				return (Report)in.readObject();
			} catch (IOException e) {
				logger.info("read object in data file throw IOException",e);
			} catch (ClassNotFoundException e) {
				logger.info("read object in data file throw ClassNotFoundException",e);
			}
			
		}
		return new Report();
	}
	String[] readStrings;//用于快速访问每行字串
	ScanFile scan;
	static Logger logger =Logger.getLogger("com.yping.classes.TdcsTask");	
}
