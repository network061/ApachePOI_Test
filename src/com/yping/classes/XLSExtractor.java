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
 * @author ��ƽ
 * @time 
 */
public class XLSExtractor {

	/**
	 * ��д��String xlsPath�����Ĺ��캯��,IterateOver���첻�˷�̫�������ĵ�����Ĵ���,
	 * �ص����ھ���������ӱ��������ݡ�
	 * @param xlsFile
	 * @param logPath
	 */
	public XLSExtractor(FileInputStream xlsFile,int[] colsIndex,String logPath){
		DOMConfigurator.configure(logPath);
		logger.info("[INFO]"+ Time.now());
		this.colsIndex = colsIndex;
		
		try {
			wb = new HSSFWorkbook(xlsFile);
		} catch (IOException e) {
			logger.info("new HSSFWordbook(xlsFile): IOException",e);
		}
		
	}
	/**
	 * ��ȡָ����Ԫ���ֵ
	 * @param aCell
	 */
	public String getCellValue(Cell aCell){
		
		if( aCell!=null){
			String cellValue = null;
			String logInfo = null;
			switch(aCell.getCellType()){
			case Cell.CELL_TYPE_NUMERIC:
				cellValue = String.valueOf(aCell.getNumericCellValue());
				logInfo = "����,Value: "+ cellValue;
				break;
			case Cell.CELL_TYPE_STRING:
				//10Ϊ���з�
				cellValue = deleteChar(10,aCell.getStringCellValue().trim());
				logInfo = "�ı�,Value: " + cellValue;
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				cellValue = String.valueOf(aCell.getBooleanCellValue());
				logInfo = "����,Value: "+ cellValue;
				break;
			case Cell.CELL_TYPE_FORMULA:
				cellValue = aCell.getCellFormula();
				logInfo = "��ʽ,Value: " + cellValue;
				break;
			case Cell.CELL_TYPE_BLANK:
				cellValue = " ";
				logInfo = "�հ�,Value: "+ cellValue;
				break;
			case Cell.CELL_TYPE_ERROR:
				cellValue = "aCell type is error";
				logInfo = "����,Value: "+ cellValue;
			    break;
			default:
				cellValue = "Unknown";
				logInfo = "δ֪,Value: Unknown";
			}
	
			logger.info("("+aCell.getRowIndex()+","+aCell.getColumnIndex()+") - " + logInfo);
			return cellValue;
		}
		
		return null;

	}	
	/**
	 * ͨ��ָ��ĳ�Ź������ĳһ�м�¼��ȡĳһ�е�����
	 * @param sheet    ָ��������
	 * @param rowIndex ��¼���±�
	 * @param colIndex ���±�
	 * @return ���ص�Ԫ�����Cell
	 */
	public Cell getCell(HSSFSheet sheet,int rowIndex,int colIndex){
		if(null != sheet && null != sheet.getRow(rowIndex)){
			return sheet.getRow(rowIndex).getCell(colIndex);
		}
		return null;
	}
	
	
	/**
	 * ��������Ϣ���������������txt�ļ�
	 * @param output
	 */
	public void output(PrintWriter output){
		int sheetsCounter = wb.getNumberOfSheets();
		for(int sheetIndex= sheetsCounter -1 ;sheetIndex >= 0;sheetIndex--){
			
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
	 * ��Դ�ִ����и���,�����ƴ���ָ���ַ��滻Ϊ�ո�
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
	int[] colsIndex; //��Ҫ���������ļ��е�����
	static Logger logger = Logger.getLogger("com.yping.classes.IterateOver");
}
