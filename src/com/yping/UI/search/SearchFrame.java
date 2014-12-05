package com.yping.UI.search;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.yping.UI.GBC;
import com.yping.UI.TDCSMenuBar;
import com.yping.UI.UIConfig;
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
		data = new Files();
		UICfg = new UIConfig();
		DOMConfigurator.configure(data.getLogPath()+"SearchFrameLogger.xml");
		
		//��ʼ����swing���
		menuBar = new TDCSMenuBar(data);
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
		searchRecords = new JRadioButton("����������Ϣ");
		searchRecords.setSelected(true);
		searchReports = new JRadioButton("������������");
		//ButtonGroup���һ�鰴ť,ʹ֮һ��ֻ��ѡ��һ��
		ButtonGroup group = new ButtonGroup();
	    group.add(searchRecords);
	    group.add(searchReports);
		searchButton = new JButton("Go");
		searchButton.addActionListener(new searchAction());
		searchLabel = new JLabel();
		
		radioPanel = new JPanel();
		radioPanel.add(searchRecords);
		radioPanel.add(searchReports);
		
		add(new JLabel("��������ҹؼ���:"),new GBC(0,0,1,1));
		add(searchField,new GBC(1,0,1,1).setFill(GBC.HORIZONTAL).setWeight(100,0));
		add(radioPanel,new GBC(2,0,1,1));
		add(searchButton,new GBC(3,0,1,1).setWeight(0,0));
		add(searchLabel,new GBC(4,0,1,1).setWeight(0,0));
		add(new JScrollPane(tdcsTable),new GBC(0,1,5,4).setFill(GBC.BOTH).setWeight(100,100));
	}
	public void search(){
		String regex = ",|��| |\\s+|��";
		String[] keys= searchField.getText().toString().trim().split(regex);
		
		if(keys.length != 0){
			TdcsTask task = new TdcsTask(data.getLogPath()+"TdcsTaskLogger.xml");
			logger.info(Time.now()+" search:"+Arrays.toString(keys));
			
			String[] result = null;
			if(searchRecords.isSelected()){
				result = task.doSearchRecord(keys,data.listResultFiles());
				if(result.length != 0){
					tdcsTable.update(UICfg.getRecordTableTitles(),result,UICfg.getRecordTableColumns());
				}	
			}else{
				result = task.doSearchReport(keys,data.listReportDatas());
				if(result.length != 0){
					tdcsTable.update(UICfg.getReportTableTitles(),result,UICfg.getReportTableColumns());
				}	
			}
			searchLabel.setText("About " + result.length + " results.");
			logger.info("About " + result.length + " results.");	
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
				search();
			}
		}
		@Override
		public void keyReleased(KeyEvent e) {}
		
	}
	Files data;
	UIConfig UICfg;
	TDCSTable tdcsTable;
    TDCSMenuBar menuBar;
    
    JButton searchButton;
	JLabel searchLabel;		//��ʾ����ƥ������
	JTextField searchField;
	JRadioButton searchRecords;
	JRadioButton searchReports;
	JPanel radioPanel;
    
    static Logger logger =Logger.getLogger("com.yping.UI.SearchFrame");
}
