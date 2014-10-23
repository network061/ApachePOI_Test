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
	 * Ϊÿһ������(��������Ϣ�г��ֵĸ���TDCS/CTCҵ������)��������
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
	File termsDoc; //Ҫ���������Ĺؼ����ĵ�
	File postingsListsDoc; //�洢�������ĵ�
	String[] terms; //���ڴ洢�ؼ���
	File[] docs;   //resultĿ¼���ĵ�
	ScanFile scan;
	static Logger logger =Logger.getLogger("com.yping.classes.InfoRetrieval");
}
