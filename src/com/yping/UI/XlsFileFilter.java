package com.yping.UI;

import java.io.File;
import javax.swing.filechooser.*;

public class XlsFileFilter extends FileFilter {

	@Override
	public boolean accept(File aFile) {
		
		if(aFile.isDirectory() || aFile.getName().endsWith(".xls")){
			return true;
		}
		return false;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Just excel 2003";
	}

}
