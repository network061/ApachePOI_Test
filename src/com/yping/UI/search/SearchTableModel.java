package com.yping.UI.search;

import javax.swing.table.AbstractTableModel;

public class SearchTableModel extends AbstractTableModel {
	
	public SearchTableModel(String[] data){
		this.rowCount = data.length;
		columnNames = new String[]{"描述","接收时间","处理过程","故障原因","备注"};
		colCount = columnNames.length;
		this.tableData = data;
		this.data = new String[rowCount][colCount];
		
		selectColumns(tableData,new int[]{3,4,5,6,9});
	}
	/**
	 * 对找到的每条记录的字段进行筛选
	 * @param source
	 * @param colIndexes
	 */
    public void selectColumns(String[] source,int[] colIndexes){
    	String[] row ;

    	for(int rowIndex = 0; rowIndex < rowCount; rowIndex ++){
    		row =  source[rowIndex].split("\\|");
    		for(int colIndex = 0; colIndex < colCount;colIndex++){
				//对data中每条记录进行split操作得到字串数组,根据新的索引挑选表格数据
	    		//将表格数据存储入数组result
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
