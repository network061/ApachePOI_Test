package com.yping.util;

import java.io.File;
import java.util.LinkedList;

/**
 * �Ż�ÿ���������ж�ȡxls�ļ��Ĵ���
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
	 * ��ȡĿ¼�����е��ӱ���ļ�����
	 * @return
	 */
	public File[] listXlsFiles(){
		return listFiles(settings.getValue(SysConstants.XLS_FILES_DIRECTORY));
	}
	/**
	 * ��ȡDatasĿ¼�ļ�
	 * @return
	 */
	public File[] listDataFiles(){
		return listFiles(getDatasPath());
	}
	/**
	 * ��ȡResultĿ¼�ļ�
	 * @return �����ļ���������
	 */
	public File[] listResultFiles(){
		 File subDir = new File(settings.getValue(SysConstants.XLS_RESULTS_DIRECTORY));
		 String[] dirNames = subDir.list();
		 LinkedList<File> result_files = new LinkedList<File>();
		 
		 for(int i=0;i < dirNames.length;++i){
			 File file = new File(subDir.getPath(),dirNames[i]);
			 //Ŀ¼�ж�,���ResultĿ¼�°��������ļ�,���ᵼ�µ���listFilesʱnullPointException��
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
	 * ��ȡlog4j��־�ļ�Ŀ¼
	 * @return
	 */
	public String getLogPath(){
		
		return settings.getValue(SysConstants.DEBUG_LOGS_DIRECTORY);
	}
	public String getXlsPath(){
		return settings.getValue(SysConstants.XLS_FILES_DIRECTORY);
	}
	/**
	 * ����txt�ĵ��Ĵ��·��
	 * @return
	 */
	public String getDatasPath(){
		return settings.getValue(SysConstants.XLS_DATAS_DIRECTORY);
	}
	/**
	 * ����resultĿ¼��ָ�������Ŀ¼·��
	 * @return
	 */
	public String getResultPath(String year){
		return settings.getValue(SysConstants.XLS_RESULTS_DIRECTORY).concat(year+ "/");
	}
	public String getResultPath(){
		return settings.getValue(SysConstants.XLS_RESULTS_DIRECTORY);
	}
	/**
	 * ����vocabularies.txt�ĵ�·��
	 * @return
	 */
	public String getTermsDocPath() {
		return settings.getValue(SysConstants.TERMS_DOC);
	}
	/**
	 * ����vocabularies.txt�ĵ�����
	 * @return
	 */
	public File getTermsDoc(){
		return new File(getTermsDocPath());
	}
	Settings settings ;
	
	
}
