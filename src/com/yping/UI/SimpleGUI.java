package com.yping.UI;
import java.awt.BorderLayout;
import javax.swing.*;

import com.yping.classes.InfoRetrieval;
import com.yping.util.Files;

public class SimpleGUI {
	
	public SimpleGUI(){
		frame = new JFrame();
		data = new Files();
		IR = new InfoRetrieval(data.getTermsDoc(),data.listResultFiles());
		TDCSMenuBar menuBar = new TDCSMenuBar(frame,data,IR.getTerms());
		menuBar.addMenuItem();
		frame.setJMenuBar(menuBar);
		
		tdcsTable = new TDCSTable();
		searchPanel = new SearchPanel(data,tdcsTable);
		searchPanel.addItem();
		
		frame.getContentPane().add(searchPanel,BorderLayout.NORTH);
		frame.getContentPane().add(new JScrollPane(tdcsTable));
			
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(960,480);
		frame.setTitle("不良信息 Search Engine by 杨平");
		frame.setVisible(true);
	}
	
	JFrame frame;
	SearchPanel searchPanel;
    TDCSTable tdcsTable;
    
    Files data;	
    InfoRetrieval IR;
    String[] title;   
}
