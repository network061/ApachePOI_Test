package com.yping.UI;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.yping.classes.InfoRetrieval;
import com.yping.classes.TdcsTask;
import com.yping.util.Files;
import com.yping.util.Time;
/**
 * ����һ��˵�����һ���ı���ǩ��һ���ı������һ����ť��һ�����������ʾ��ǩ��һ�������ʾ��������
 * @author ��ƽ��
 * update 20141017  �������񲼾ֹ������,����SearchPanel��SimpleGUI������Ĺ���,��ɾ������������,�򻯴��롣
 *
 */
public class SearchFrame extends JFrame {
	public SearchFrame(){
		DOMConfigurator.configure("./loggerConfig/SearchFramelLogger.xml");
		//��ʼ��������
		data = new Files();
		IR = new InfoRetrieval(data.getTermsDoc(),data.listResultFiles());
		
		//��ʼ����swing���
		menuBar = new TDCSMenuBar(this,data,IR.getTerms());
		setJMenuBar(menuBar);
		tdcsTable = new TDCSTable();
		
		//�������񲼾�
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		addComponents();
		
		setSize(960,480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("������Ϣ Search Engine by ��ƽ");
		setVisible(true);
	}
	
	public void addComponents(){
		searchField = new JTextField();
		searchField.setFocusable(true);
		searchField.addKeyListener(new searchKeyListener());
		searchButton = new JButton("����������Ϣ");
		searchButton.addActionListener(new searchAction());
		searchLabel = new JLabel();
		
		add(new JLabel("��������ҹؼ���:"),new GBC(0,0,1,1));
		add(searchField,new GBC(1,0,1,1).setFill(GBC.HORIZONTAL).setWeight(100,0));
		add(searchButton,new GBC(2,0,1,1).setWeight(0,0));
		add(searchLabel,new GBC(3,0,1,1).setWeight(0,0));
		add(new JScrollPane(tdcsTable),new GBC(0,1,4,4).setFill(GBC.BOTH).setWeight(100,100));
	}
	public void search(){
		String regex = ",|��| |\\s+|��";
		String[] keys= searchField.getText().toString().trim().split(regex);
		TdcsTask task = new TdcsTask();
		logger.info(Time.now()+" search:"+Arrays.toString(keys));
		
		String[] result = null;
		result = task.doSearch(keys,data.listResultFiles());
		searchLabel.setText("About " + result.length + " results.");
		logger.info("About " + result.length + " results.");
		if(result.length != 0){
			tdcsTable.update(result);
		}	
	}
	class searchAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			search();
		}
	}
	class searchKeyListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {}
		@Override
		public void keyPressed(KeyEvent e) {
			//�س������º��������
			int keyCode = e.getKeyCode();
			if(keyCode == KeyEvent.VK_ENTER){
				System.out.println("enter...");
				search();
			}
		}
		@Override
		public void keyReleased(KeyEvent e) {}
		
	}
	TDCSTable tdcsTable;
    TDCSMenuBar menuBar;
    
    JButton searchButton;
	JLabel searchLabel;		//��ʾ����ƥ������
	JTextField searchField;
	  
	Files data;	
    InfoRetrieval IR;
    
    static Logger logger =Logger.getLogger("com.yping.UI.SearchFrame");
}
