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
		
		/*默认的渲染器继承自JLabel,不利于多行显示,需要自定义继承JTextArea的渲染器
		 *并重新设置表格的渲染器。
		 */
		setDefaultRenderer(Object.class,new TextareaCellRenderer());
		repaint();
	}

	String[] title;
	SearchTableModel tableModel;
}
