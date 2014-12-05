package com.yping.classes;

import java.io.Serializable;

public class Report implements Serializable{
	public Report(){
		setDocPath("");
		setText("");
		setImageNames(new String[]{""});
	}
	public String getDocPath() {
		return docPath;
	}
	public void setDocPath(String docPath) {
		this.docPath = docPath;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public String[] getImageNames() {
		return imageNames;
	}
	public void setImageNames(String[] imageNames) {
		this.imageNames = imageNames;
	}

	private String docPath;
	private String text;
	private String[] imageNames;
}
