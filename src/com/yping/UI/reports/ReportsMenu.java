package com.yping.UI.reports;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.yping.classes.DOCExtractor;
import com.yping.classes.PDFExtractor;

public class ReportsMenu extends JMenu {
	public ReportsMenu(String menuTitle,String reportPath,String DocDataPath,String PdfDataPath,String imagesSavePath,String logPath){
		super(menuTitle);
		this.reportPath = reportPath;
		this.docDatPath = DocDataPath;
		this.logPath = logPath;
		this.imagesSavePath = imagesSavePath;
		loadItem = new JMenuItem("load doc/pdf file");
		loadItem.addActionListener(new loadDocAction());
		add(loadItem);
		displayItem = new JMenuItem("display report");
		add(displayItem);
		frame = new ReportsFrame();
		displayItem.addActionListener(new displayAction());
	}
	
	class displayAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(frame != null && !frame.isVisible()){
				frame.setVisible(true);
			}
		}
	}
	class loadDocAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fc = new JFileChooser(reportPath);
			fc.setMultiSelectionEnabled(true);
			fc.setAcceptAllFileFilterUsed(false);
//			fc.setFileFilter(new TDCSFileFilter(new String[]{".doc",".pdf"},new String[]{"Just Word 2003"," PDF"}));
			int returnVal = fc.showOpenDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION){
				File[] reportFiles = fc.getSelectedFiles();
				if(reportFiles.length != 0){
					for(File report:reportFiles){
						doExtract(report);
					}
				}
			}
		}
		
	}
	public void doExtract(File file){
		if(file.getName().contains(".doc")){
			docExtractor = new DOCExtractor(file,docDatPath.concat(file.getName()),imagesSavePath,logPath+"DOCExtractorLogger.xml");
		}else if(file.getName().contains(".pdf")){
			pdfExtractor = new PDFExtractor(file,pdfDatPath.concat(file.getName()),logPath+"PDFExtractorLogger.xml");
			pdfExtractor.output();
		}
	}
	JMenuItem loadItem;
	JMenuItem displayItem;
	ReportsFrame frame;
	
	String reportPath;
	String docDatPath;
	String imagesSavePath;
	String pdfDatPath;
	String logPath;
	
	PDFExtractor pdfExtractor ;
	DOCExtractor docExtractor;
}
