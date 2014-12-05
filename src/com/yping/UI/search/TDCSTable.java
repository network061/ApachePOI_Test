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
		
		/*默认的渲染器继承自JLabel,不利于多行显示,需要自定义继承JTextArea的渲染器
		 *并重新设置表格的渲染器。
		 */
		setDefaultRenderer(Object.class,new TextareaCellRenderer());
		repaint();
	}

	String[] title;
	SearchTableModel tableModel;
}
