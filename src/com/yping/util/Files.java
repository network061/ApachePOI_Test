package com.yping.util;

import java.io.File;
import java.util.LinkedList;

/**
 * �Ż�ÿ���������ж�ȡxls�ļ��Ĵ���
 * ����listXXXFiles()��ʾ��ȡXXXĿ¼�ļ�����
 * ����getXXXPath()��ʾ��ȡ��ΪXXX��Ŀ¼·��
 * @author Administrator
 *
 */
public class Files {
	
	public Files(){
		settings = new Settings(SysConstants.SYS_PROPERTIES);
		files = new LinkedList<File>();
	}
	
	public File[] listFiles(String directory){
		
		return new File(directory).listFiles();
	}

	public File[] listDataFiles(){
		return listFiles(getXlsDatasPath());
	}
	
	public File[] listResultFiles(){
		return getAllFiles(new File(getResultPath()));
	}
	public File[] listReportFiles(){
		return getAllFiles(new File(getReportsPath()));
	}
	public String getLogPath(){	
		return settings.getValue(SysConstants.DEBUG_LOGS_DIRECTORY);
	}
	public String getXlsPath(){
		return settings.getValue(SysConstants.XLS_FILES_DIRECTORY);
	}
	
	public String getXlsDatasPath(){
		return settings.getValue(SysConstants.XLS_DATAS_DIRECTORY);
	}
	public String getDocDataPath() {
		return settings.getValue(SysConstants.DOC_DATAS_DIRECTORY);
	}
	public String getResultPath(){
		return settings.getValue(SysConstants.XLS_RESULTS_DIRECTORY);
	}
	
	public String getReportsPath(){
		return settings.getValue(SysConstants.DOC_REPORTS_DIRECTORY);
	}
	public String getDocResultsPath() {
		return settings.getValue(SysConstants.DOC_RESULTS_DIRECTORY);
	}
	/**
	 * ����vocabularies.txt�ĵ�����
	 * @return
	 */
	public File getTermsDoc(){
		return new File(settings.getValue(SysConstants.TERMS_DOC));
	}
	
	private void listAllFiles(File f){
		if(f!=null){
			if(f.isDirectory()){
				File[] fileArray = f.listFiles();
				if(fileArray.length != 0){
					for(File file:fileArray){
					    listAllFiles(file);	
					}
				}
			}else{
				files.add(f);
			}
		}
	}
	public File[] getAllFiles(File f){
		listAllFiles(f);
		if(!files.isEmpty()){
			File[] results = new File[files.size()];
			files.toArray(results);
			return results;
		}
		return new File[]{};
	}
	LinkedList<File> files;
	Settings settings ;
	
}
