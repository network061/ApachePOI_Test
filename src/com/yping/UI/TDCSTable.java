package com.yping.UI;

import javax.swing.JTable;

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
