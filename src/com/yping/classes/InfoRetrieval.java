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
	 * Ϊÿһ������(��������Ϣ�г��ֵĸ���TDCS/CTCҵ������)��������
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
				// TODO vocabularies.txt��ȡ�쳣
				e.printStackTrace();
				logger.info(Time.now()+"-vocabularies.txt��ȡ�쳣��");
			}
			result = new String[terms.size()];
			return terms.toArray(result);
		}
		return new String[]{""};
	}
	File termsDoc; //Ҫ���������Ĺؼ����ĵ�
	File postingsListsDoc; //�洢�������ĵ�
	String[] terms; //���ڴ洢�ؼ���
	File[] docs;   //resultĿ¼���ĵ�
	static Logger logger =Logger.getLogger("com.yping.classes.InfoRetrieval");
}
