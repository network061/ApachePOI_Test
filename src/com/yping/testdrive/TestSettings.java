package com.yping.testdrive;

import com.yping.util.Settings;
import com.yping.util.SysConstants;

public class TestSettings {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Settings settings = new Settings(SysConstants.SYS_PROPERTIES);
		System.out.println(settings.getValue(SysConstants.XLS_RESULTS_DIRECTORY));

	}

}
