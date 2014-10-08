package com.yping.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Rule {
	public Rule(String sourceStr,File aFile){
		source = sourceStr;
		file = aFile;
	}
	
	public boolean match(){
		try {
			in = new Scanner(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	String source;
	File file;
	Scanner in;
	
}
