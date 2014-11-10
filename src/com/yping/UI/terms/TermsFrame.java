package com.yping.UI.terms;

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

import com.yping.UI.GBC;
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
		displayResult.setText("Ŀǰ����"+termsList.getModel().getSize()+"���ؼ��֡�");
	}
	class addAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			logger.info("[INFO]"+Time.now()+"-�û����add��ť");
			logger.info("[INFO]����û�����");
			if(inputField.getText().equals("")){
				Toolkit.getDefaultToolkit().beep();
				logger.info("[INFO]�û�����Ϊ��");
				return ;
			}else{
				String input = inputField.getText().trim();
				logger.info("[INFO]�û�����:"+input);
				if(!termsList.alreadyInList(input)){
					termsList.addTerm(input);
					logger.info("[INFO]�û�����Ϸ�,�Ѽ���model����");
					resultUpdate();
				}else{
					displayResult.setText("�ؼ����Ѵ���!");
				}
			}
			logger.info("[INFO]add��ť  �¼���Ӧ��ɡ�");
		}	
	}
	class saveAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			logger.info("[INFO]"+Time.now()+"-�û����save��ť�������ļ�");
			termsList.saveAsFile();
			logger.info("[INFO]save��ť  �¼���Ӧ��ɡ�");
		}
	}
	class removeAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			logger.info("[INFO]"+Time.now()+"-�û����remove��ť�Ƴ��ؼ���:");
			termsList.delTerms(String.valueOf(termsList.getSelectedValue()));
			logger.info("[INFO]remove��ť  �¼���Ӧ��ɡ�");
			resultUpdate();
		}	
	}
	TermsList termsList;
	JTextField inputField; //���뵥��
	JButton addButton; 	   //���ӵ���
	JButton removeButton;  //�Ƴ�����
	JButton saveButton;    //��ListModel�еĵ��ʱ�����vocabularies.txt
	JLabel displayResult;  //��ʾ�������
	static Logger logger =Logger.getLogger("com.yping.UI.TermsFrame");
}
