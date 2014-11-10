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
		loadXlsItem = new JMenuItem("���d��ӱ��");   //���ص��ӱ���ļ�
		insertDataItem = new JMenuItem("���");
		
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
	 * ����IterateOver��ķ��������ӱ�����Ϊtxt�ĵ���
	 * @param aXls
	 * @return ���ؽ�����ɵ�txt�ĵ�
	 */
	public File doIterator(File aXls) {
		FileInputStream fileIn = null; //����xlsPath��������FileInputStream����
		PrintWriter output = null;     //��������ļ�·��,IterateOver����writeData��������
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
			// TODO ���ָ���ļ����ڣ�������һ��Ŀ¼��������һ�������ļ������߸��ļ������ڣ����޷����������ֻ���Ϊ����ĳЩԭ����޷�����
			e.printStackTrace();
		}

		colsIndex = new int[] {1,2,3,4,5,6,8,12,13};
		iterate = new XLSExtractor(fileIn,colsIndex,logCfgPath+"IterateOverLogger.xml");
		iterate.output(output); 
		return new File(txtFileName);
	}
	/**
	 * ��txt�ĵ������ظ���¼����,���洢��ResultĿ¼�¡�
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
