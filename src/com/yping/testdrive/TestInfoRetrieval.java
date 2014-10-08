package com.yping.testdrive;

import com.yping.classes.InfoRetrieval;
import com.yping.util.Files;

public class TestInfoRetrieval {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Files files = new Files();
		InfoRetrieval IR = new InfoRetrieval(files.getTermsDoc(),files.listResultFiles());
		String[] terms = IR.getTerms();
		for(String term:terms){
			System.out.println(term);
		}
	}

}
