package com.yping.classes;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import com.yping.util.Time;

/**
 * Iterater over rows and cell
 * @author 杨平
 * @time 
 */
public class IterateOver {

	/**
	 * 重写带String xlsPath参数的构造函数,IterateOver构造不宜放太多生成文档对象的代码,
	 * 重点在于尽快读出电子表格对象数据。
	 * @param xlsFile
	 * @param logPath
	 */
	public IterateOver(FileInputStream xlsFile,int[] colsIndex,String logPath){
		DOMConfigurator.configure(logPath);
		logger.info("[INFO]"+ Time.now());
		this.colsIndex = colsIndex;
		
		try {
			logger.info("创建HSSFWorkbook对象wb,参数为fileIn");
			wb = new HSSFWorkbook(xlsFile);
		} catch (IOException e) {
			logger.info("IOException 构造HSSFWorkbook异常",e);
		}
		
	}
	
	/**
	 * 获取工作簿中所有的工作表数量
	 * @return
	 */
	public int  getNumberOfSheets(){
		return wb.getNumberOfSheets();	
	}
	
	/**
	 * 获取指定单元格的值
	 * @param aCell
	 */
	public String getCellValue(Cell aCell){
		
		if( aCell!=null){
			String cellValue = null;
			String logInfo = null;
			switch(aCell.getCellType()){
			case Cell.CELL_TYPE_NUMERIC:
				cellValue = String.valueOf(aCell.getNumericCellValue());
				logInfo = "数字,Value: "+ cellValue;
				break;
			case Cell.CELL_TYPE_STRING:
				//10为换行符
				cellValue = deleteChar(10,aCell.getStringCellValue().trim());
				logInfo = "文本,Value: " + cellValue;
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				cellValue = String.valueOf(aCell.getBooleanCellValue());
				logInfo = "布尔,Value: "+ cellValue;
				break;
			case Cell.CELL_TYPE_FORMULA:
				cellValue = aCell.getCellFormula();
				logInfo = "公式,Value: " + cellValue;
				break;
			case Cell.CELL_TYPE_BLANK:
				cellValue = " ";
				logInfo = "空白,Value: "+ cellValue;
				break;
			case Cell.CELL_TYPE_ERROR:
				cellValue = "aCell type is error";
				logInfo = "错误,Value: "+ cellValue;
			    break;
			default:
				cellValue = "Unknown";
				logInfo = "未知,Value: Unknown";
			}
	
			logger.info("("+aCell.getRowIndex()+","+aCell.getColumnIndex()+") - " + logInfo);
			return cellValue;
		}
		
		return null;

	}
	
	
	/**
	 * 通过指定某张工作表的某一行记录获取某一列的内容
	 * @param sheet    指定工作表
	 * @param rowIndex 记录行下标
	 * @param colIndex 列下标
	 * @return 返回单元格对象Cell
	 */
	public Cell getCell(HSSFSheet sheet,int rowIndex,int colIndex){
		if(null != sheet && null != sheet.getRow(rowIndex)){
			return sheet.getRow(rowIndex).getCell(colIndex);
		}
		return null;
	}
	
	
	/**
	 * 将不良信息工作簿数据输出到txt文件
	 * @param output
	 */
	public void writeData(PrintWriter output){
		for(int sheetIndex= getNumberOfSheets() -1 ;sheetIndex >= 0;sheetIndex--){
			
			for(int rowIndex = wb.getSheetAt(sheetIndex).getLastRowNum();rowIndex > 2;rowIndex--){
				output.print(sheetIndex);
				output.print("|");
				for(int colIndex:colsIndex){
				 Cell cell = getCell(wb.getSheetAt(sheetIndex),rowIndex,colIndex);
				 if(null != cell){
					 output.print(getCellValue(cell));
					 output.print("|");
				 }
			    }
				output.println();
			}
		}
		output.flush();
		output.close();
	}
	/**
	 *debug该工作簿概要数据
	 */
	public void getSummary(){
		int rowCounter = 0;
		HSSFSheet sheet = null;
		
		for(int sheetIndex =0 ;sheetIndex < getNumberOfSheets();sheetIndex++){
			logger.info("调用wb对象的getSheetAt(sheetIndex)方法,得到第"+(sheetIndex+1)+"张工作表.");
			rowCounter = wb.getSheetAt(sheetIndex).getLastRowNum();
			
			sheet = wb.getSheetAt(sheetIndex);
			for(int rowIndex = 3;rowIndex<=rowCounter;rowIndex++){
			  if(null != getCell(sheet, rowIndex,0)){
				  getCellValue(getCell(sheet, rowIndex,0));
			  }
			}
		}
	}
	
	/**
	 * 把源字串进行复制,将复制串中指定字符替换为空格。
	 * @param deleteChar
	 * @param src
	 * @return
	 */
	public String deleteChar(int deleteChar,String src){
		StringBuffer strBuf = new StringBuffer();
		
		for(int index = 0;index < src.length();++index){
			if(src.codePointAt(index)!= deleteChar){
				strBuf.append(src.charAt(index));
			}else{
				strBuf.append(" ");
			}
		}
		return strBuf.toString();
	}
	HSSFWorkbook wb ;
	int[] colsIndex; //需要工作表中哪几列的数据
	static Logger logger = Logger.getLogger("com.yping.classes.IterateOver");
	Time time;
}
