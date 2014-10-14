package com.yping.UI;

import javax.swing.JTable;

public class TDCSTable extends JTable {
	
	public TDCSTable() {		
		title = new String[]{"����","����ʱ��","�������","����ԭ��","��ע"};
		tableModel = new SearchTableModel(title,new String[]{});
		setModel(tableModel);
	}

	public void update(String[] results){
		setModel(new SearchTableModel(title,results));
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
