package com.yping.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import com.yping.util.Time;

public class PDFExtractor {
	public PDFExtractor(File pdfFile,String destinationPath,String logCfgPath){
		DOMConfigurator.configure(logCfgPath);
		logger.info("[INFO]"+ Time.now()+"[Exact File]"+pdfFile.getPath());
		this.pdfFile = pdfFile;
		this.destinationPath = destinationPath.replace(".pdf",".dat");
		try {
			pdDoc = PDDocument.load(pdfFile);
		} catch (IOException e) {
			logger.info("PDDocument.load(File file) throw IOException",e);
		}
		
	}
	public void output(){
		Report report = new Report();
		ObjectOutputStream outStream = null;
		try {
			outStream = new ObjectOutputStream(new FileOutputStream(destinationPath));
		} catch (FileNotFoundException e1) {
			logger.info("new ObjectOutputStream throw FileNotFoundException.",e1);
		} catch (IOException e1) {
			logger.info("new ObjectOutputStream throw IOException.",e1);
		}
		
		if(report != null && outStream !=null){
			logger.info("Data Output["+destinationPath+"]");
			report.setDocPath(pdfFile.getPath());
			report.setText(getText());
			logger.info(report.getText());
			try {
				outStream.writeObject(report);
				outStream.close();
			} catch (IOException e) {
				logger.info("ObjectOutputStream writeObject method throw IOException.",e);
			}
		}
	}
	public void isEncrypted(){
		logger.info("Document is encrypted:"+pdDoc.isEncrypted());
	}
	public String getText(){
		PDFTextStripper textStripper = null;
		try {
			textStripper = new PDFTextStripper("utf-8");
		} catch (IOException e) {
			logger.info("PDFExtractor.java line:34 new PDFTextStripper(String encoding) throw IOExcetion.",e);
		}
		if(textStripper != null){
			try {
				textStripper.setLineSeparator("\n");
				return textStripper.getText(pdDoc);
			} catch (IOException e) {
				logger.info("TextStripper.getText(PDDocument pdDoc) throw a IOException",e);
			}
		}
		return "blank";
	}
	public void getPictures(){
		
	}
	private File pdfFile;
	private PDDocument  pdDoc;
	private String destinationPath;
	static Logger logger = Logger.getLogger("com.yping.classes.PDFExtractor");
}
