package com.yping.UI;

import java.awt.Component;

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
			// 计算当下行的最佳高度 
			int maxPreferredHeight = 0; 
			for (int i = 0; i < table.getColumnCount(); i++) { 
			setText("" + table.getValueAt(row, i)); 
			setSize(table.getColumnModel().getColumn(column).getWidth(), 0); 
			maxPreferredHeight = Math.max(maxPreferredHeight, getPreferredSize().height); 
			} 

			if (table.getRowHeight(row) != maxPreferredHeight) {
				table.setRowHeight(row, maxPreferredHeight);
			}
			 

			setText(value == null ? "" : value.toString()); 
			return this; 
			} 

}
