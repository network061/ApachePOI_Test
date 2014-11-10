package com.yping.util;

import java.io.File;
import javax.swing.filechooser.*;

public class TDCSFileFilter extends FileFilter {
	public TDCSFileFilter(String suffix,String description){
		this.suffix = suffix;
		this.description = description;
	}
	
	@Override
	public boolean accept(File aFile) {
		
		if(aFile.isDirectory() || aFile.getName().endsWith(suffix)){
			return true;
		}
		return false;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}
	
	String suffix;
	String description;
}
