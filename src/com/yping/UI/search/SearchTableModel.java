package com.yping.UI.search;

import javax.swing.table.AbstractTableModel;

public class SearchTableModel extends AbstractTableModel {
	
	public SearchTableModel(String[] tableTitle,String[] data,int[] selectedCols){
		this.rowCount = data.length;
		columnNames = tableTitle;
		colCount = columnNames.length;
		this.tableData = data;
		this.data = new String[rowCount][colCount];
		
		selectColumns(tableData,selectedCols);
	}
	/**
	 * ���ҵ���ÿ����¼���ֶν���ɸѡ
	 * @param source
	 * @param colIndexes
	 */
    public void selectColumns(String[] tableData,int[] colIndexes){
    	String[] row ;
    	for(int rowIndex = 0; rowIndex < rowCount; rowIndex ++){
    		row =  tableData[rowIndex].split("\\|");
    		for(int colIndex = 0; colIndex < colCount;colIndex++){
				//��data��ÿ����¼����split�����õ��ִ�����,�����µ�������ѡ�������
	    		//��������ݴ洢������result
			  try{
				data[rowIndex][colIndex] = row[colIndexes[colIndex]];
			  }catch(ArrayIndexOutOfBoundsException e){
			  }
	    	}		
		}
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return rowCount;
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return colCount;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return data[rowIndex][columnIndex];
	}
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return columnNames[column];
	}
	int rowCount;
	int colCount;
	String[] columnNames;
	String[][] data;
	String[] tableData;
}
