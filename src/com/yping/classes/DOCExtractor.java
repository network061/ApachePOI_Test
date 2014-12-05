package com.yping.classes;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
	public DOCExtractor(File file,String destinationPath,String imagesSavePath,String logCfgPath){
		DOMConfigurator.configure(logCfgPath);
		this.fileName = file.getName();
		this.dataPath = destinationPath.replace(".doc",".dat");
		dataPath_image = imagesSavePath.concat(file.getName().replace(".doc","")+File.separator);
		logger.info("[INFO]"+ Time.now()+"[Exact File]"+file.getPath());
		try {
			wordDoc = new HWPFDocument(new FileInputStream(file.getPath()));
		} catch (FileNotFoundException e) {
			logger.info("new FileInputStrem():FileNotFoundException.",e);
		} catch (IOException e) {
			logger.info("IOException",e);
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
	private Picture[] getPictures() {
		List<Picture> picturesList = wordDoc.getPicturesTable().getAllPictures();
		Picture[] pics = new Picture[picturesList.size()];
	    return picturesList.toArray(pics);
	}
	private void createImgFile(BufferedImage image,String imagePath){
		try {
			logger.info("Image save as "+imagePath);
			ImageIO.write(image,"jpg",new File(imagePath));
		} catch (IOException e) {
			logger.info("ImageIO.write Image["+imagePath+"] throw IOException",e);
		}
	}
	private void checkImgDir(String path){
		File imgDir = new File(path);
		if(!imgDir.exists()){
			imgDir.mkdirs();
		}
	}
	/**
	 * Serialization
	 */
    public void output(){
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
			String[] imageNames = new String[picsSize];
			ArrayList<String> imageNamesList = new ArrayList<String>();
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
		    		imageNamesList.add(dataPath_image+"Image["+i+"]."+pics[i].suggestFileExtension());
		    		checkImgDir(dataPath_image);
		    		createImgFile(image,dataPath_image+"Image["+i+"]."+pics[i].suggestFileExtension());
		    	}else{
		    		imageNamesList.add(dataPath_image+"unsupported image type.jpg");
		    	}
		    }
			imageNamesList.toArray(imageNames);
			report.setImageNames(imageNames);
			try {
				outStream.writeObject(report);
				outStream.close();
			} catch (IOException e) {
				logger.info("ObjectOutputStream writeObject method throw IOException.",e);
			}
		}
    }
	HWPFDocument wordDoc;
	String dataPath;      //data output path
	String dataPath_image;//image output path     
	String fileName;     // word file name
	static Logger logger = Logger.getLogger("com.yping.classes.DOCExtractor");
}
