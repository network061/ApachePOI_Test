package com.yping.UI.search;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.yping.classes.DataScraping;
import com.yping.classes.XLSExtractor;
import com.yping.dao.LoadDriver;
import com.yping.util.ScanFile;
import com.yping.util.TDCSFileFilter;

public class SearchMenu extends JMenu {
	public SearchMenu(String title,String logCfgPath,String xlsPath,String datasPath,String resultPath){
		super(title);
		this.logCfgPath = logCfgPath;
		this.xlsPath = xlsPath;
		this.resultPath = resultPath;
		this.datasPath = datasPath;
		
		addMenuItems();
		updateDB = new LoadDriver();
	}
	public void addMenuItems(){
		loadXlsItem = new JMenuItem("加載電子表格");   //加载电子表格文件
		insertDataItem = new JMenuItem("入庫");
		
		loadXlsItem.addActionListener(new loadXlsAction());
		insertDataItem.addActionListener(new insertAction());
		
		add(loadXlsItem);
		addSeparator();
		add(insertDataItem);
	}
	class loadXlsAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fc = new JFileChooser(xlsPath);
			fc.setMultiSelectionEnabled(false);
			fc.setAcceptAllFileFilterUsed(false);
			fc.setFileFilter(new TDCSFileFilter(".xls","Just EXCEL 2003"));
			int returnVal = fc.showOpenDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION){
				File xlsFile = fc.getSelectedFile();
				if(xlsFile != null){
					doDataScraping(doIterator(xlsFile));
				}
			}
		}
		
	}
	class insertAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fc = new JFileChooser(resultPath);
			fc.setMultiSelectionEnabled(true);
			fc.setAcceptAllFileFilterUsed(false);
			fc.setFileFilter(new TDCSFileFilter(".txt","Just TXT"));
			updateDB.openConn();
			int returnVal = fc.showOpenDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION){
				File[] selectedTxts = fc.getSelectedFiles();
				for(File aTxt:selectedTxts){
					updateDB.insertRecords(new ScanFile(aTxt).scan());
				}
			}
		}
		
	}
	
	/**
	 * 调用IterateOver类的方法将电子表格解析为txt文档。
	 * @param aXls
	 * @return 返回解析完成的txt文档
	 */
	public File doIterator(File aXls) {
		FileInputStream fileIn = null; //根据xlsPath参数构造FileInputStream对象
		PrintWriter output = null;     //数据输出文件路径,IterateOver对象writeData方法参数
		XLSExtractor iterate = null;  	
		int[] colsIndex = null;
		try {
			fileIn = new FileInputStream(aXls.getPath());
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String txtFileName = datasPath.concat(aXls.getName().replace("xls","txt"));
		try {
			output = new PrintWriter(txtFileName);
		} catch (IOException e) {
			// TODO 如果指定文件存在，但它是一个目录，而不是一个常规文件；或者该文件不存在，但无法创建它；抑或因为其他某些原因而无法打开它
			e.printStackTrace();
		}

		colsIndex = new int[] {1,2,3,4,5,6,8,12,13};
		iterate = new XLSExtractor(fileIn,colsIndex,logCfgPath+"IterateOverLogger.xml");
		iterate.output(output); 
		return new File(txtFileName);
	}
	/**
	 * 对txt文档进行重复记录过滤,并存储于Result目录下。
	 * @param aTxt
	 */
	public void doDataScraping(File aTxt){
		DataScraping dataScraping = new DataScraping(aTxt,logCfgPath + "DataScrapingLogger.xml");
		dataScraping.output(resultPath);
	}
	JMenuItem loadXlsItem;
	JMenuItem insertDataItem;
	String logCfgPath;
	String xlsPath;
	String resultPath;
	String datasPath;
	LoadDriver updateDB;
}
