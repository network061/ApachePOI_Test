package com.yping.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Scanner;

public class TdcsTask {

	public TdcsTask(){
		
	}
	
	public TdcsTask(Scanner in){
		this.in = in;
		lines = new LinkedList<String>();
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
	 * �������ĵ�docs���ҳ����йؼ���keys
	 * @param keys
	 * @param docs
	 */
	public String[] doSearchV2(String[] keys,File[] docs){
		lines = new LinkedList<String>();
		for(File doc:docs){
			try {
				in = new Scanner(new FileReader(doc));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			while(in.hasNextLine()){
				String line = in.nextLine();
				int counter =0;
				for(String key:keys){
					if(!line.contains(key)){
						break; 
					}
					++ counter;
				}
				if(counter == keys.length){
					lines.add(line);
				}
			}
		}
		String[] result = new String[lines.size()];
		return lines.toArray(result);
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
		getData();
		transformData();
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
