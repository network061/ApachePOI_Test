package com.yping.UI.search;

import javax.swing.JTable;

import com.yping.UI.TextareaCellRenderer;

public class TDCSTable extends JTable {
	
	public TDCSTable() {	
		tableModel = new SearchTableModel(new String[]{});
		setModel(tableModel);
	}

	public void update(String[] results){
		setModel(new SearchTableModel(results));
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
