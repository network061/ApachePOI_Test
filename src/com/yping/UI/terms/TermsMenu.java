package com.yping.UI.terms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class TermsMenu extends JMenu {
	public TermsMenu(String title,String termsDocPath,String[] words){
		super(title);
		this.words = words;
		this.termsDocPath = termsDocPath;
		frame = new TermsFrame(termsDocPath,words);
		editList = new JMenuItem("�༭�ؼ���");
		editList.addActionListener(new addTermAction());
		generateIndexes = new JMenuItem("�������");
		generateIndexes.addActionListener(new generateAction());
		add(editList);
	}
	
	class addTermAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(frame != null && !frame.isVisible()){
				frame.setVisible(true);
			}
		}
	}
	class generateAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
		
	}
	JMenuItem editList;
	JMenuItem generateIndexes;
	String[] words;//JList�ĳ�ʼ������
	String termsDocPath;
	TermsFrame frame;
}
