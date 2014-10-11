package com.yping.UI;

import java.awt.Component;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

public class TextareaCellRenderer extends JTextArea implements
		TableCellRenderer {
	public TextareaCellRenderer(){
		setLineWrap(true);
		setWrapStyleWord(true);
	}
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		setText((String) value);
		setSize(table.getColumnModel().getColumn(column).getWidth(),getPreferredSize().height);
		if(table.getRowHeight(row) != getPreferredSize().height){
			table.setRowHeight(row,getPreferredSize().height);
		}
//		return new JScrollPane(this);
		table.getColumnModel().getColumn(1).setPreferredWidth(4);
		table.getColumnModel().getColumn(4).setPreferredWidth(2);
		return this;
	}

}
