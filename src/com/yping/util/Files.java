package com.yping.util;

import java.io.File;
import java.util.LinkedList;

/**
 * 优化每个测试类中读取xls文件的代码
 * @author Administrator
 *
 */
public class Files {
	
	public Files(){
		settings = new Settings(SysConstants.SYS_PROPERTIES);
	}
	
	public File[] listFiles(String directory){
		
		return new File(directory).listFiles();
	}
	/**
	 * 获取目录中所有电子表格文件对象
	 * @return
	 */
	public File[] listXlsFiles(){
		return listFiles(settings.getValue(SysConstants.XLS_FILES_DIRECTORY));
	}
	/**
	 * 获取Datas目录文件
	 * @return
	 */
	public File[] listDataFiles(){
		return listFiles(getDatasPath());
	}
	/**
	 * 获取Result目录文件
	 * @return 返回文件对象数组
	 */
	public File[] listResultFiles(){
		 File subDir = new File(settings.getValue(SysConstants.XLS_RESULTS_DIRECTORY));
		 String[] dirNames = subDir.list();
		 LinkedList<File> result_files = new LinkedList<File>();
		 
		 for(int i=0;i < dirNames.length;++i){
			 File file = new File(subDir.getPath(),dirNames[i]);
			 //目录判断,如果Result目录下包含其它文件,不会导致调用listFiles时nullPointException　
			 if(file.isDirectory()){
				 for(File aTXT:file.listFiles()){
					 result_files.push(aTXT);
				 }
			 }
		 }
		 File[] fileArray = new File[result_files.size()];
		return result_files.toArray(fileArray);	
	}
	/**
	 * 获取log4j日志文件目录
	 * @return
	 */
	public String getLogPath(){
		
		return settings.getValue(SysConstants.DEBUG_LOGS_DIRECTORY);
	}
	public String getXlsPath(){
		return settings.getValue(SysConstants.XLS_FILES_DIRECTORY);
	}
	/**
	 * 返回txt文档的存放路径
	 * @return
	 */
	public String getDatasPath(){
		return settings.getValue(SysConstants.XLS_DATAS_DIRECTORY);
	}
	/**
	 * 返回result目录下指定年份子目录路径
	 * @return
	 */
	public String getResultPath(String year){
		return settings.getValue(SysConstants.XLS_RESULTS_DIRECTORY).concat(year+ "/");
	}
	public String getResultPath(){
		return settings.getValue(SysConstants.XLS_RESULTS_DIRECTORY);
	}
	/**
	 * 返回vocabularies.txt文档路径
	 * @return
	 */
	public String getTermsDocPath() {
		return settings.getValue(SysConstants.TERMS_DOC);
	}
	/**
	 * 返回vocabularies.txt文档对象
	 * @return
	 */
	public File getTermsDoc(){
		return new File(getTermsDocPath());
	}
	Settings settings ;
	
	
}
