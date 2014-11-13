package com.yping.UI.reports;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.yping.util.TDCSFileFilter;

public class ReportsMenu extends JMenu {
	public ReportsMenu(String menuTitle,String reportPath,String logPath){
		super(menuTitle);
		this.reportPath = reportPath;
		this.logPath = logPath;
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
			fc.setMultiSelectionEnabled(false);
			fc.setAcceptAllFileFilterUsed(false);
			fc.setFileFilter(new TDCSFileFilter(".doc","Just Word 2003"));
			int returnVal = fc.showOpenDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION){
				File docFile = fc.getSelectedFile();
				if(docFile != null){
					doExtract(docFile);
				}
			}
		}
		
	}
	public void doExtract(File f){
		
		//DOCExtractor docExtractor = new DOCExtractor(f.getPath(),logPath+"DOCExtractorLogger.xml");
		//docExtractor.getText();
	}
	JMenuItem loadItem;
	JMenuItem displayItem;
	ReportsFrame frame;
	
	String reportPath;
	String logPath;
}
