package com.yping.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import com.yping.classes.DataScraping;
import com.yping.classes.IterateOver;
import com.yping.dao.LoadDriver;
import com.yping.util.Files;


public class TDCSMenuBar extends JMenuBar{
	public TDCSMenuBar(JFrame frame,Files data,String[] words){	
		this.frame = frame;
		this.data = data;
		this.words = words;
		updateDB = new LoadDriver();
	}
	public void addMenuItem(){
		JMenu fileMenu = new JMenu("不良信息數據");
		
		JMenuItem loadXlsItem = new JMenuItem("加載電子表格");   //加载电子表格文件
		JMenuItem insertDataItem = new JMenuItem("入庫");
		
		loadXlsItem.addActionListener(new loadXlsAction());
		insertDataItem.addActionListener(new insertAction());
		
		fileMenu.add(loadXlsItem);
		fileMenu.addSeparator();
		fileMenu.add(insertDataItem);
		
		add(fileMenu);
		add(new TermsMenu("不良信息关键字",data.getTermsDocPath(),words));
	}
	class loadXlsAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fc = new JFileChooser(data.getXlsPath());
			fc.setMultiSelectionEnabled(false);
			fc.setAcceptAllFileFilterUsed(false);
			fc.setFileFilter(new XlsFileFilter());
			int returnVal = fc.showOpenDialog(frame);
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
			JFileChooser fc = new JFileChooser(data.getResultPath());
			fc.setMultiSelectionEnabled(true);
			fc.setAcceptAllFileFilterUsed(false);
//			fc.setFileFilter(new ResultFileFilter());
			updateDB.openConn();
			int returnVal = fc.showOpenDialog(frame);
			if(returnVal == JFileChooser.APPROVE_OPTION){
				File[] selectedTxts = fc.getSelectedFiles();
				for(File aTxt:selectedTxts){
					
					updateDB.insertRecords(doScan(aTxt));
				}
			}
		}

		private String[] doScan(File aTxt) {
			Scanner in = null;
			LinkedList<String> lines = new LinkedList<String>();
			try {
				in = new Scanner(new FileReader(aTxt));
				while(in.hasNextLine()){
					lines.add(in.nextLine());
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String[] linesArr = new String[lines.size()];
			return lines.toArray(linesArr);
		}
		
	}
	
	/**
	 * 调用IterateOver类的方法将电子表格解析为txt文档。
	 * @param aXls
	 * @return 返回解析完成的txt文档
	 */
	public File doIterator(File aXls) {
		FileInputStream fileIn = null; //根据xlsPath参数构造FileInputStream对象
		String logPath = null;		   //IterateOver类构造方法参数
		PrintWriter output = null;     //数据输出文件路径,IterateOver对象writeData方法参数
		IterateOver iterate = null;  	
		int[] colsIndex = null;
		try {
			fileIn = new FileInputStream(aXls.getPath());
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		logPath = data.getLogPath()+"IterateOverLogger.xml";
		String txtFileName = data.getDatasPath().concat(aXls.getName().replace("xls","txt"));
		try {
			output = new PrintWriter(txtFileName);
		} catch (IOException e) {
			// TODO 如果指定文件存在，但它是一个目录，而不是一个常规文件；或者该文件不存在，但无法创建它；抑或因为其他某些原因而无法打开它
			e.printStackTrace();
		}

		colsIndex = new int[] {1,2,3,4,5,6,8,12,13};
		iterate = new IterateOver(fileIn,colsIndex,logPath);
		iterate.writeData(output); 
		return new File(txtFileName);
	}
	/**
	 * 对txt文档进行重复记录过滤,并存储于Result目录下。
	 * @param aTxt
	 */
	public void doDataScraping(File aTxt){
		Scanner in = null;         //调用DataScraping对象readData方法参数一
		String logPath = null;     //初始化DataScraping类的日志参数
		DataScraping dataScraping = null;
		String argu_year = null;
		String argu_yearAndMonth = null;
		String argu_path = null;
		try {
			in = new Scanner(new FileReader(aTxt));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logPath = data.getLogPath()+"DataScrapingLogger.xml";
		dataScraping = new DataScraping(in,logPath);
		try {
			argu_year = aTxt.getName().substring(0,aTxt.getName().indexOf("年"));
			argu_yearAndMonth = aTxt.getName().substring(0,aTxt.getName().indexOf("月")).replace("年","-");
			argu_path = data.getResultPath(argu_year).concat(aTxt.getName());
			dataScraping.readData(new PrintWriter(argu_path),argu_yearAndMonth+"-");
		} catch (FileNotFoundException e) {
			// TODO test.txt创建失败
			e.printStackTrace();
		}
	}
	JFrame frame;
	Files data;
	LoadDriver updateDB;
	String[] words; //JList数据初始化
}
