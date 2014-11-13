package com.yping.testdrive;

import java.io.File;

import com.yping.classes.DOCExtractor;
import com.yping.util.Files;

public class TestDOCExtractor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Files data = new Files();
		File[] files = data.listReportFiles();
		String dataPath = data.getDocDataPath();
		String objStorePath = data.getDocResultsPath();
		for(File file:files){
			
			if(file.getName().contains(".doc")){
				System.out.println(file.getName());
				DOCExtractor docExtractor = new DOCExtractor(file.getPath(),dataPath.concat(file.getName()),objStorePath,data.getLogPath()+"DOCExtractorLogger.xml");
				docExtractor.outputV2();
			}
		}
	}

}
