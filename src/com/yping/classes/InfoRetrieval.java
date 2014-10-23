package com.yping.classes;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashSet;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.yping.util.ScanFile;

public class InfoRetrieval {

	public InfoRetrieval(File termsDoc,File[] files){
		DOMConfigurator.configure("./loggerConfig/InfoRetrievalLogger.xml");
		this.termsDoc = termsDoc;
		this.docs  = files;
	}
	/**
	 * 为每一个单词(即不良信息中出现的各种TDCS/CTC业务术语)创建索引
	 */
	public void generatePostings(PrintWriter output){
		// Todo something
	}
	
	public String[] getTerms(){
		
		if (termsDoc != null){
			HashSet<String> terms = new HashSet<String>() ;
			String[] result = null;
			scan = new ScanFile(termsDoc);
			String[] lines= scan.scan();
			for(String line:lines){
				terms.add(line);
			}
			
			result = new String[terms.size()];
			return terms.toArray(result);
		}
		return new String[]{""};
	}
	File termsDoc; //要建立索引的关键字文档
	File postingsListsDoc; //存储索引的文档
	String[] terms; //用于存储关键字
	File[] docs;   //result目录下文档
	ScanFile scan;
	static Logger logger =Logger.getLogger("com.yping.classes.InfoRetrieval");
}
