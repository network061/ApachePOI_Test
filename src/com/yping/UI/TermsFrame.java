package com.yping.UI;

import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.yping.util.Time;

public class TermsFrame extends JFrame {

	public TermsFrame(String  termsDocPath,String[] terms){
		DOMConfigurator.configure("./loggerConfig/TermsFrame/log4jConfig.xml");
		termsList = new TermsList(termsDocPath,terms);
		
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);	
		
		inputField = new JTextField();
		
		addButton = new JButton("add");
		addButton.addActionListener(new addAction());
		
		removeButton = new JButton("remove");
		removeButton.addActionListener(new removeAction());
		
		saveButton = new JButton("save");
		saveButton.addActionListener(new saveAction());
		
		displayResult = new JLabel();
		
		add(new JLabel("Add item:"),new GBC(0,0,1,1));
		add(inputField,new GBC(1,0,1,1).setFill(GBC.HORIZONTAL).setWeight(100,0));
		add(addButton,new GBC(2,0,1,1).setWeight(0,0));
		add(new JScrollPane(termsList),new GBC(0,1,3,4).setFill(GBC.BOTH).setWeight(100,100));
		add(removeButton,new GBC(1,5).setAnchor(GBC.EAST).setInsets(1,1,1,7));
		add(saveButton,new GBC(2,5));
		add(displayResult,new GBC(0,5));
		
		resultUpdate();
		setSize(800,600);
		setTitle("vocabularies list");
		setResizable(false);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setVisible(false);
	}
	public void resultUpdate(){
		displayResult.setText("目前共有"+termsList.getModel().getSize()+"个关键字。");
	}
	class addAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			logger.info("[INFO]"+Time.now()+"-用户点击add按钮");
			logger.info("[INFO]检查用户输入");
			if(inputField.getText().equals("")){
				Toolkit.getDefaultToolkit().beep();
				logger.info("[INFO]用户输入为空");
				return ;
			}else{
				String input = inputField.getText().trim();
				logger.info("[INFO]用户输入:"+input);
				if(!termsList.alreadyInList(input)){
					termsList.addTerm(input);
					logger.info("[INFO]用户输入合法,已加入model对象。");
					resultUpdate();
				}else{
					displayResult.setText("关键字已存在!");
				}
			}
			logger.info("[INFO]add按钮  事件响应完成。");
		}	
	}
	class saveAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			logger.info("[INFO]"+Time.now()+"-用户点击save按钮保存至文件");
			termsList.saveAsFile();
			logger.info("[INFO]save按钮  事件响应完成。");
		}
	}
	class removeAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			logger.info("[INFO]"+Time.now()+"-用户点击remove按钮移除关键字:");
			termsList.delTerms(String.valueOf(termsList.getSelectedValue()));
			logger.info("[INFO]remove按钮  事件响应完成。");
			resultUpdate();
		}	
	}
	TermsList termsList;
	JTextField inputField; //输入单词
	JButton addButton; 	   //增加单词
	JButton removeButton;  //移除单词
	JButton saveButton;    //将ListModel中的单词保存至vocabularies.txt
	JLabel displayResult;  //显示操作结果
	static Logger logger =Logger.getLogger("com.yping.UI.TermsFrame");
}
