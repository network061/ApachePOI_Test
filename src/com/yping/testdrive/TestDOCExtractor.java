package com.yping.testdrive;

import java.io.File;

import com.yping.classes.DOCExtractor;
import com.yping.util.Files;

public class TestDOCExtractor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Files data = new Files();
		File[] files = data.listReportFiles();
		String dataPath = data.getDocDataPath();
		String imagesSavePath = data.getDocImagesPath();
		System.out.println("Doc data path:"+dataPath);
		for(File file:files){			
			if(file.getName().contains(".doc")){
				System.out.println(file.getName());
				DOCExtractor docExtractor = new DOCExtractor(file,dataPath.concat(file.getName()),imagesSavePath,data.getLogPath()+"DOCExtractorLogger.xml");
				docExtractor.output();
			}
		}
	}

}
