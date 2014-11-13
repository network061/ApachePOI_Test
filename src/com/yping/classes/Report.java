package com.yping.classes;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Report implements Serializable{
	public Report(){
		setDocPath("");
		setText("");
		setImages(new BufferedImage[]{});
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
	public BufferedImage[] getImages() {
		return images;
	}
	public void setImages(BufferedImage[] images) {
		this.images = images;
	}
	private String docPath;
	private String text;
	private BufferedImage[] images;
}
