package com.yping.UI.reports;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.yping.classes.Report;
import com.yping.util.Time;

public class ReportsTextPane extends JTextPane {
	public ReportsTextPane(){
		super();
		setEditable(false);
		DOMConfigurator.configure("./loggerConfig/ReportsTextPaneLogger.xml");
		logger.info(Time.now());
		doc = getStyledDocument();
	}
	public void displayReport(String datPath){
		Report report = getReport(datPath);
		try {
			doc.insertString(doc.getLength(),getText(report),doc.getStyle("regular"));
		} catch (BadLocationException e) {
			 logger.info("Couldn't insert initial text into text pane. BadLocationException");
		}
		displayImages(getImages(report));
	}
	public void displayImages(ImageIcon[] icons){
		if(icons.length != 0){
			for(ImageIcon icon:icons){
				insertIcon(icon);
			}	
		}
	}
	private void addStylesToDoc(StyledDocument doc){
		 //Initialize some styles.
        Style def = StyleContext.getDefaultStyleContext().
                        getStyle(StyleContext.DEFAULT_STYLE);
        StyleConstants.setFontFamily(def, "SansSerif");
        doc.addStyle("regular", def);
	}
	public ImageIcon[] getImages(Report report){
		String[] imageNames = report.getImageNames();
		ImageIcon[] icons = new ImageIcon[imageNames.length];
		ArrayList<ImageIcon> iconsList = new ArrayList<ImageIcon>();
		for(String path:imageNames){
			iconsList.add(new ImageIcon(path));
		}
		return iconsList.toArray(icons);
	}
	public Report getReport(String docPath){
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(docPath));
		} catch (FileNotFoundException e) {
			logger.info("new ObjectInputStream throw FileNotFoundException.",e);
		} catch (IOException e) {
			logger.info("new ObjectInputStream throw IOException.",e);
		}
		if(in != null){
			logger.info("create ObjectInputStream obj ["+docPath+"] successed.");
			try {
				return (Report)in.readObject();
			} catch (IOException e) {
				logger.info("read object in data file throw IOException",e);
			} catch (ClassNotFoundException e) {
				logger.info("read object in data file throw ClassNotFoundException",e);
			}
			
		}
		return new Report();
	}
	public String getText(Report report){
		if(report != null){
			logger.info(report.getText());
			return report.getText();
		}else{
			logger.info("ObjectInputStream readObject() failed.");
		}
		return "blank";	
	}
	public void removeCurrentDoc() {
		if(doc != null && doc.getLength() != 0){
			try {
				doc.remove(0,doc.getLength());
			} catch (BadLocationException e) {
				logger.info("Remove Current Content throw BadLocationException.");
			}
		}	
	}
	StyledDocument doc ;
	static Logger logger =Logger.getLogger("com.yping.UI.ReportsTextPane");	
}
