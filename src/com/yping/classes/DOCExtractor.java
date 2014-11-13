package com.yping.classes;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.usermodel.Picture;

import com.yping.util.Time;

public class DOCExtractor {
	public DOCExtractor(String fileName,String dataPath,String objStorePath,String logCfgPath){
		DOMConfigurator.configure(logCfgPath);
		this.fileName = fileName;
		this.dataPath = dataPath.replace(".doc",".dat");
		this.objStorePath = objStorePath;
		logger.info("[INFO]"+ Time.now()+"[Exact File]"+fileName);
		try {
			wordDoc = new HWPFDocument(new FileInputStream(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.info("new FileInputStrem():FileNotFoundException.");
		} catch (IOException e) {
			logger.info("IOException");
			e.printStackTrace();
		}
	}
	public String getText(){
		WordExtractor extractor = new WordExtractor(wordDoc);
		String[] text = extractor.getParagraphText();
		int lineCounter = text.length;
		StringBuilder articleStr = new StringBuilder();
		for(int index = 0;index < lineCounter;++ index){
			String paragraphStr = text[index].replaceAll("\r\n","").replaceAll("\n","").trim();
			int paragraphLength = paragraphStr.length();
			if(paragraphLength != 0){
				articleStr.append(paragraphStr+"\n");
			}
		}
		return articleStr.toString();
	}
	public Picture[] getPictures() {
		List<Picture> picturesList = wordDoc.getPicturesTable().getAllPictures();
		Picture[] pics = new Picture[picturesList.size()];
	    return picturesList.toArray(pics);
	}
	/**
	 * Serialization
	 */
    public void outputV2(){
		Picture[] pics = getPictures();
		Report report = new Report();
		ObjectOutputStream outStream = null;
		try {
			outStream = new ObjectOutputStream(new FileOutputStream(dataPath));
		} catch (FileNotFoundException e1) {
			logger.info("new ObjectOutputStream throw FileNotFoundException.",e1);
		} catch (IOException e1) {
			logger.info("new ObjectOutputStream throw IOException.",e1);
		}
		
		if(report != null && outStream !=null){
			logger.info("Data Output["+dataPath+"]");
			report.setDocPath(fileName);
			report.setText(getText());
			int picsSize = pics.length;
			BufferedImage[] images = new BufferedImage[picsSize];
			ArrayList<BufferedImage> imageList = new ArrayList<BufferedImage>();
			for(int i=0;i<picsSize;++i){
		    	BufferedImage image = null;
		    	try {
					image = ImageIO.read(new ByteArrayInputStream(pics[i].getContent()));
				}catch(IIOException e){
					logger.info("Image["+i+"] new ByteArrayInputStream():IIOException Unsurpported Image type"+" Suggest Image Format:"+pics[i].suggestFileExtension(),e);
				}catch (IOException e) {
					logger.info("Image["+i+"] new ByteArrayInputStream():IOException Suggest Image Format:"+pics[i].suggestFileExtension(),e);
				} 
		    	if(image != null){
		    		logger.info("Image["+i+"] ImageWidth:"+image.getWidth()+" ImageHeight:"+image.getHeight()+" Suggest Image Format:"+pics[i].suggestFileExtension());
		    		imageList.add(image);
		    	}else{
		    		imageList.add(null);
		    	}
		    }
			imageList.toArray(images);
			//report.setImages(images);
			try {
				outStream.writeObject(report);
				outStream.close();
			} catch (IOException e) {
				logger.info("ObjectOutputStream writeObject method throw IOException.",e);
			}
		}
    }
	public void output(){
		PrintWriter output = null;
		Picture[] pics = getPictures();
		try {
			output = new PrintWriter(dataPath);
		} catch (FileNotFoundException e) {
			logger.info("new PrinterWriter(File f)[FileNotFoundException]",e);
		}
		if(output !=null){
			logger.info("Data Output["+dataPath+"]");
			output.print(fileName+"|"+getText()+"|"+pics.length+"|");
			for(int i=0;i<pics.length;++i){
		    	BufferedImage image = null;
		    	try {
					image = ImageIO.read(new ByteArrayInputStream(pics[i].getContent()));
				}catch(IIOException e){
					logger.info("Image["+i+"] new ByteArrayInputStream():IIOException Unsurpported Image type"+" Suggest Image Format:"+pics[i].suggestFileExtension(),e);
					output.print("null"+"|");
				}catch (IOException e) {
					logger.info("Image["+i+"] new ByteArrayInputStream():IOException Suggest Image Format:"+pics[i].suggestFileExtension(),e);
					output.print("null"+"|");
				} 
		    	if(image != null){
		    		logger.info("Image["+i+"] ImageWidth:"+image.getWidth()+" ImageHeight:"+image.getHeight()+" Suggest Image Format:"+pics[i].suggestFileExtension());
		    		File imgDir = new File(dataPath.replace(".txt",""));
		    		if(!imgDir.exists()){
		    			imgDir.mkdirs();
		    		}
		    		try {
		    			String imgPath = imgDir.getPath().concat(File.separator+"Image"+i+".jpg");
		    			logger.info("Image save as "+imgPath);
						ImageIO.write(image,"jpg",new File(imgPath));
						output.print("Image"+i+".jpg|");
					} catch (IOException e) {
						logger.info("ImageIO.write Image["+i+"] throw IOException",e);
					}
		    	}
		    }
		    output.println();
		    output.flush();
			output.close();
		}
		
	}
	HWPFDocument wordDoc;
	String dataPath;      //data output path
	String objStorePath; //data store path
	String fileName;     // word file name
	static Logger logger = Logger.getLogger("com.yping.classes.DOCExtractor");
}
