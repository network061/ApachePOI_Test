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
						break; //只要包含其中一个关键字则表示结果正确查找,退出循环进入下一条记录的比较
					}
				}
			}
		}
		String[] result = new String[lines.size()];
		return lines.toArray(result);
	}
	/**
	 * 版本2更新:
	 * 1.对每个关键字的查找结果使用数组保存,用CollectionUtil查找intersection
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
	 * 判断某个文档中哪些行包含key
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
					break; //只要包含其中一个关键字则表示结果正确查找,退出循环进入下一条记录的比较
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
	 * 统计每个月的不良信息数量
	 * @param regx 正则匹配
	 * @param index 对不良信息某列进行匹配
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
	LinkedList<String> lines;//用于存储文档字串行
	String[] readStrings;//用于快速访问每行字串
}
