package com.yping.UI.search;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.yping.UI.GBC;
import com.yping.UI.TDCSMenuBar;
import com.yping.classes.TdcsTask;
import com.yping.util.Files;
import com.yping.util.Time;
/**
 * 包括一组菜单栏、一个文本标签、一个文本输入框、一个按钮、一个搜索结果显示标签和一个结果显示表格组件。
 * @author 楊平　
 * update 20141017  利用网格布局管理界面,整合SearchPanel、SimpleGUI两个类的工作,并删除上述两个类,简化代码。
 *
 */
public class SearchFrame extends JFrame {
	public SearchFrame(){
		data = new Files();
		DOMConfigurator.configure(data.getLogPath()+"SearchFrameLogger.xml");
		
		//初始化各swing组件
		menuBar = new TDCSMenuBar(data);
		setJMenuBar(menuBar);
		tdcsTable = new TDCSTable();
		
		//设置网格布局
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		addComponents();
		
		setSize(960,480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("不良信息 Search Engine by 杨平");
		setVisible(true);
	}
	
	public void addComponents(){
		searchField = new JTextField();
		searchField.setFocusable(true);
		searchField.addKeyListener(new searchKeyListener());
		searchButton = new JButton("搜索不良信息");
		searchButton.addActionListener(new searchAction());
		searchLabel = new JLabel();
		
		add(new JLabel("请输入查找关键字:"),new GBC(0,0,1,1));
		add(searchField,new GBC(1,0,1,1).setFill(GBC.HORIZONTAL).setWeight(100,0));
		add(searchButton,new GBC(2,0,1,1).setWeight(0,0));
		add(searchLabel,new GBC(3,0,1,1).setWeight(0,0));
		add(new JScrollPane(tdcsTable),new GBC(0,1,4,4).setFill(GBC.BOTH).setWeight(100,100));
	}
	public void search(){
		String regex = ",|，| |\\s+|　";
		String[] keys= searchField.getText().toString().trim().split(regex);
		if(keys.length != 0){
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
			//回车键按下后进行搜索
			int keyCode = e.getKeyCode();
			if(keyCode == KeyEvent.VK_ENTER){
				search();
			}
		}
		@Override
		public void keyReleased(KeyEvent e) {}
		
	}
	Files data;
	TDCSTable tdcsTable;
    TDCSMenuBar menuBar;
    
    JButton searchButton;
	JLabel searchLabel;		//显示查找匹配数量
	JTextField searchField;
    
    static Logger logger =Logger.getLogger("com.yping.UI.SearchFrame");
}
