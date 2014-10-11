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
		JMenu fileMenu = new JMenu("������Ϣ����");
		
		JMenuItem loadXlsItem = new JMenuItem("���d��ӱ��");   //���ص��ӱ���ļ�
		JMenuItem insertDataItem = new JMenuItem("���");
		
		loadXlsItem.addActionListener(new loadXlsAction());
		insertDataItem.addActionListener(new insertAction());
		
		fileMenu.add(loadXlsItem);
		fileMenu.addSeparator();
		fileMenu.add(insertDataItem);
		
		add(fileMenu);
		add(new TermsMenu("������Ϣ�ؼ���",data.getTermsDocPath(),words));
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
	 * ����IterateOver��ķ��������ӱ�����Ϊtxt�ĵ���
	 * @param aXls
	 * @return ���ؽ�����ɵ�txt�ĵ�
	 */
	public File doIterator(File aXls) {
		FileInputStream fileIn = null; //����xlsPath��������FileInputStream����
		String logPath = null;		   //IterateOver�๹�췽������
		PrintWriter output = null;     //��������ļ�·��,IterateOver����writeData��������
		IterateOver iterate = null;  	
		int[] colsIndex = null;
		try {
			fileIn = new FileInputStream(aXls.getPath());
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
//		logPath = data.getLogPath().concat(aXls.getName().replace("xls","log"));
		logPath = data.getLogPath()+"IterateOverLogger.xml";
		String txtFileName = data.getDatasPath().concat(aXls.getName().replace("xls","txt"));
		try {
			output = new PrintWriter(txtFileName);
		} catch (IOException e) {
			// TODO ���ָ���ļ����ڣ�������һ��Ŀ¼��������һ�������ļ������߸��ļ������ڣ����޷����������ֻ���Ϊ����ĳЩԭ����޷�����
			e.printStackTrace();
		}

		colsIndex = new int[] {1,2,3,4,5,6,8,12,13};
		iterate = new IterateOver(fileIn,colsIndex,logPath);
		iterate.writeData(output); 
		return new File(txtFileName);
	}
	/**
	 * ��txt�ĵ������ظ���¼����,���洢��ResultĿ¼�¡�
	 * @param aTxt
	 */
	public void doDataScraping(File aTxt){
		Scanner in = null;         //����DataScraping����readData��������һ
		String logPath = null;     //��ʼ��DataScraping�����־����
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
			argu_year = aTxt.getName().substring(0,aTxt.getName().indexOf("��"));
			argu_yearAndMonth = aTxt.getName().substring(0,aTxt.getName().indexOf("��")).replace("��","-");
			argu_path = data.getResultPath(argu_year).concat(aTxt.getName());
			dataScraping.readData(new PrintWriter(argu_path),argu_yearAndMonth+"-");
		} catch (FileNotFoundException e) {
			// TODO test.txt����ʧ��
			e.printStackTrace();
		}
	}
	JFrame frame;
	Files data;
	LoadDriver updateDB;
	String[] words; //JList���ݳ�ʼ��
}
