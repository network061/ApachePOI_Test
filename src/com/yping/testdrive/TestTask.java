package com.yping.testdrive;

import com.yping.classes.TdcsTask;
import com.yping.util.Files;

public class TestTask {

	/**
	 * ≤‚ ‘TdcsTask¿‡
	 * @param args
	 */
	public static void main(String[] args) {
		
		Files data = new Files();
		TdcsTask task = new TdcsTask(data.getLogPath().concat("TdcsTaskLogger.xml"));
		String[] result = null;
		result = task.doSearchRecord(new String("∂´›∏").split("°°"),data.listResultFiles());
		System.out.println("About " + result.length + " results.");
		String[] result1 = result[0].split("\\|");
		System.out.println(result1.length);
		for(String str:result1){
//			System.out.println(str);
		}
		for(String str:result){
			System.out.println(str);
		}
	}

}
