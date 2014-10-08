package com.yping.UI;

import javax.swing.JTable;

public class TDCSTable extends JTable {
	
	public TDCSTable() {		
		title = new String[]{"�ļ���","����","����ʱ��","�������","����ԭ��","��ע"};
		tableModel = new SearchTableModel(title,new String[]{});
		setModel(tableModel);
	}

	public void update(String[] results){
		setModel(new SearchTableModel(title,results));
		setFillsViewportHeight(true);
		textareaCell = new TextareaCellRenderer();
		setTextAreaCellRenderer(new int[]{3});
		repaint();
	}

	public void setTextAreaCellRenderer(int[] colIndexes){
		for(int index:colIndexes){
			getColumnModel().getColumn(index).setCellRenderer(textareaCell);
		}
	}
	TextareaCellRenderer textareaCell;
	String[] title;
	SearchTableModel tableModel;
}
