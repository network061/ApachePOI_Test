package com.yping.classes;

import java.io.File;
import java.util.LinkedList;
import com.yping.util.ScanFile;

public class TdcsTask {

	public TdcsTask(){
		
	}
	
	/**
	 * �������ĵ�docs���ҳ����йؼ���keys
	 * @param keys
	 * @param docs
	 */
	public String[] doSearch(String[] keys,File[] docs){
		LinkedList<String> lines = new LinkedList<String>();
		for(File doc:docs){
			scan = new ScanFile(doc);
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
					lines.add(line);
				}
		    }
		}
		String[] result = new String[lines.size()];
		return lines.toArray(result);
	}

	String[] readStrings;//���ڿ��ٷ���ÿ���ִ�
	ScanFile scan;
}
