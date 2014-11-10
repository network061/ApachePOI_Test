package com.yping.UI.search;

import javax.swing.table.AbstractTableModel;

public class SearchTableModel extends AbstractTableModel {
	
	public SearchTableModel(String[] data){
		this.rowCount = data.length;
		columnNames = new String[]{"����","����ʱ��","�������","����ԭ��","��ע"};
		colCount = columnNames.length;
		this.tableData = data;
		this.data = new String[rowCount][colCount];
		
		selectColumns(tableData,new int[]{3,4,5,6,9});
	}
	/**
	 * ���ҵ���ÿ����¼���ֶν���ɸѡ
	 * @param source
	 * @param colIndexes
	 */
    public void selectColumns(String[] source,int[] colIndexes){
    	String[] row ;

    	for(int rowIndex = 0; rowIndex < rowCount; rowIndex ++){
    		row =  source[rowIndex].split("\\|");
    		for(int colIndex = 0; colIndex < colCount;colIndex++){
				//��data��ÿ����¼����split�����õ��ִ�����,�����µ�������ѡ�������
	    		//��������ݴ洢������result
			  try{
				data[rowIndex][colIndex] = row[colIndexes[colIndex]];
			  }catch(ArrayIndexOutOfBoundsException e){
				  System.out.println(row[0]+" "+ row[4] + " " + row[5]);
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
