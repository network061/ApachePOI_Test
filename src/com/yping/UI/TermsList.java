package com.yping.UI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.yping.util.Time;

public class TermsList extends JList {
	public TermsList(String termsDocPath,String[] words){
		DOMConfigurator.configure("./loggerConfig/TermsList/log4jConfig.xml");
		logger.info(Time.now()+"-��ʼ��ʼ��TermsList:");
		model = new DefaultListModel();
		terms = new LinkedList<String>();
		this.termsDocPath = termsDocPath;
		for(String term:words){
		 model.addElement(term);
		 terms.add(term);
		}
		setModel(model);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setLayoutOrientation(JList.HORIZONTAL_WRAP);
		logger.info(Time.now()+"-��ʼ��TermsList��ɡ�");
	}
	public boolean alreadyInList(String input) {
		if(model.contains(input)){
			return true;
		}
		return false;
	}
	public void addTerm(String term){
		logger.info(Time.now() +"--before insert: "+ model.getSize());
		if(model != null & terms != null){
			model.addElement(term);
			terms.add(term);
			logger.info(Time.now() +"--before insert: "+ model.getSize());
		}else{
			//model or terms be null 
			logger.info(Time.now() +" model or terms should not be null");
		}
		
	}
	public String[] getTermsArr(){
		if(terms != null){
			String[] termsArr = new String[terms.size()];
			terms.toArray(termsArr);
			return termsArr;
		}
		return new String[]{""};
	}
	public void saveAsFile() {
		termsDoc = new File(termsDocPath);
		logger.info("����vacabularies.txt:"+termsDoc.exists());
		PrintWriter output;
		try {
			output = new PrintWriter(termsDoc);
			String[] termsArr = getTermsArr();
			for(String term:termsArr){
				output.println(term);
			}
			output.flush();
			output.close();	
			logger.info("�ѽ�DefaultListModel�����б����terms���鱣�����ĵ���");
		} catch (FileNotFoundException e) {
			logger.info("FileNotFoundException...");
			e.printStackTrace();
		}
	}
	public void delTerms(String string) {
		
		model.removeElement(string);
		logger.info("ListModel��ɾ��Ԫ��:"+string);
	}
	DefaultListModel model;
	LinkedList<String> terms;
	File termsDoc;
	String termsDocPath;
	static Logger logger =Logger.getLogger("com.yping.UI.TermsList");
	
}
