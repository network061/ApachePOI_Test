package com.yping.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class TdcsTask {

	public TdcsTask(){
		
	}
	
	public TdcsTask(Scanner in){
		this.in = in;
		lines = new LinkedList<String>();
		
		getData();
		transformData();
		
	}
	public String[] doSearch(String[] keys,File[] files){
		lines = new LinkedList<String>();
		for(File file:files){
			try {
				in = new Scanner(new FileReader(file));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			while(in.hasNextLine()){
				String temp = in.nextLine();
				for(String key:keys){
					if(temp.contains(key)){
						lines.add(file.getName().replace(".txt","").concat("|"+temp));
						break; //ֻҪ��������һ���ؼ������ʾ�����ȷ����,�˳�ѭ��������һ����¼�ıȽ�
					}
				}
			}
		}
		String[] result = new String[lines.size()];
		return lines.toArray(result);
	}
	/**
	 * �汾2����:
	 * 1.��ÿ���ؼ��ֵĲ��ҽ��ʹ�����鱣��,��CollectionUtil����intersection
	 * @param keys
	 * @param files
	 * @return
	 */
	public String[] doSearchV2(String[] keys,File[] files){
		lines = new LinkedList<String>();
		
		for(File file:files){
			
		}
		String[] result = new String[lines.size()];
		return lines.toArray(result);
	}
	/**
	 * �ж�ĳ���ĵ�����Щ�а���key
	 * @param key
	 * @param line
	 * @return
	 */
	public LinkedList<String> contains(String[] keys,File aFile,PrintWriter output){
		try {
			in = new Scanner(new FileReader(aFile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(in.hasNextLine()){
			String temp = in.nextLine();
			for(String key:keys){
				if(temp.contains(key)){
//					lines.add(aFile.getName().replace(".txt","").concat("|"+temp));
					lines.add(temp.split("\\|")[0]);
					break; //ֻҪ��������һ���ؼ������ʾ�����ȷ����,�˳�ѭ��������һ����¼�ıȽ�
				}
			}
		}
		return null;
	}
	public void getData(){
		while(in.hasNextLine()){
			lines.add(in.nextLine());
		}	
	}
	public void transformData(){
		if(!lines.isEmpty()){
			readStrings = new String[lines.size()];
			lines.toArray(readStrings);
		}
	}
	public String[] getWebData(){
		return readStrings;
	}
	/**
	 * ͳ��ÿ���µĲ�����Ϣ����
	 * @param regx ����ƥ��
	 * @param index �Բ�����Ϣĳ�н���ƥ��
	 */
	public int getCounter(String regx,int index){
		int counter = 0;
		String[] tokens = null;
		for(String str:readStrings){
	    	tokens = str.split("\\|");
	    	if(tokens.length > index){
	    		if(tokens[index].contains(regx)){
		  			++counter;
		  		}
		  	}
	    }
		return counter;
	}
	
	
	Scanner in;
	LinkedList<String> lines;//���ڴ洢�ĵ��ִ���
	String[] readStrings;//���ڿ��ٷ���ÿ���ִ�
}
