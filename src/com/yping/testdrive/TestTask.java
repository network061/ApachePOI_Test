package com.yping.testdrive;

import com.yping.classes.TdcsTask;
import com.yping.util.Files;

public class TestTask {

	/**
	 * ≤‚ ‘TdcsTask¿‡
	 * @param args
	 */
	public static void main(String[] args) {
		TdcsTask task = new TdcsTask();
		Files data = new Files();
		String[] result = null;
		result = task.doSearch(new String("∂´›∏").split("°°"),data.listResultFiles());
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
