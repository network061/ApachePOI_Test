package com.yping.UI.search;

import javax.swing.JTable;

import com.yping.UI.TextareaCellRenderer;

public class TDCSTable extends JTable {
	
	public TDCSTable() {	
		tableModel = new SearchTableModel(new String[]{},new String[]{},new int[]{});
		setModel(tableModel);
	}

	public void update(String[] title,String[] results,int [] selectedCols){
		setModel(new SearchTableModel( title,results,selectedCols));
		setFillsViewportHeight(true);
		
		/*Ĭ�ϵ���Ⱦ���̳���JLabel,�����ڶ�����ʾ,��Ҫ�Զ���̳�JTextArea����Ⱦ��
		 *���������ñ�����Ⱦ����
		 */
		setDefaultRenderer(Object.class,new TextareaCellRenderer());
		repaint();
	}

	String[] title;
	SearchTableModel tableModel;
}
