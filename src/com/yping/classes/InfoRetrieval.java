package com.yping.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.yping.util.Time;

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
		terms = getTerms();
		for(String term:terms){
			logger.info(term);
			output.print(term+"|");
			termInFiles(term,docs,output);
		}
		output.flush();
		output.close();	
	}
	
	public void termInFiles(String key,File[] docs,PrintWriter output){
		Scanner in=null;
		String line = null;
		String[] tokens = null;
		for(File aTxt:docs){
			try {
				in = new Scanner(aTxt);
			} catch (FileNotFoundException e) {
				logger.info("File obj throw FileNotFoundException.");
				e.printStackTrace();
			}
			if(in != null){
				while(in.hasNextLine()){
					line = in.nextLine();
					if( line.contains(key)){
						tokens = line.split("\\|");
						output.print(tokens[tokens.length-1]+"|");
					}	
				}
				output.println();
			}		
			
		}
	}
	
	public String[] getTerms(){
		
		if (termsDoc != null){
			HashSet<String> terms = new HashSet<String>() ;
			String[] result;
			try {
				Scanner in = new Scanner(new FileReader(termsDoc));
				terms = new HashSet<String>();
				while(in.hasNextLine()){
					terms.add(in.nextLine());
				}
			} catch (FileNotFoundException e) {
				// TODO vocabularies.txt读取异常
				e.printStackTrace();
				logger.info(Time.now()+"-vocabularies.txt读取异常。");
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
	static Logger logger =Logger.getLogger("com.yping.classes.InfoRetrieval");
}
