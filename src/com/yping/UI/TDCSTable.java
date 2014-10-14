package com.yping.UI;

import javax.swing.JTable;

public class TDCSTable extends JTable {
	
	public TDCSTable() {		
		title = new String[]{"描述","接收时间","处理过程","故障原因","备注"};
		tableModel = new SearchTableModel(title,new String[]{});
		setModel(tableModel);
	}

	public void update(String[] results){
		setModel(new SearchTableModel(title,results));
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
