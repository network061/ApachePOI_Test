package com.yping.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.yping.util.Time;

public class LoadDriver {
	public LoadDriver(){
		DOMConfigurator.configure("./loggerConfig/LoadDriverLogger.xml");
		conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("[INFO]"+Time.now()+" InstantitionExcetion:����ʵ�����쳣");
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("[INFO]"+Time.now()+" IllegalAccessExcetion:�Ƿ������쳣");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("[INFO]"+Time.now()+" ClassNotFoundExcetion:com.mysql.jdbc.Driver������쳣");
		}
		
//		openConn();
		
	}
	public void openConn(){
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?"+"user=test&" +
					"useUnicode=true&characterEncoding=utf-8");
		} catch (SQLException e) {
			logger.info("[INFO]"+Time.now()+" �������ݿ�����쳣��");
			logger.info("[INFO]"+Time.now()+" SQLException: "+e.getMessage());
			logger.info("[INFO]"+Time.now()+" SQLState: "+e.getSQLState());
			logger.info("[INFO]"+Time.now()+" VendorError: "+e.getErrorCode());	
		}
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			logger.info("[INFO]"+Time.now()+" ���Ӷ������createStatement���������쳣��");
			logger.info("[INFO]"+Time.now()+" SQLException: "+e.getMessage());
			logger.info("[INFO]"+Time.now()+" SQLState: "+e.getSQLState());
			logger.info("[INFO]"+Time.now()+" VendorError: "+e.getErrorCode());
		}
	}
	public void searchRecords(String[] keys){
		String sql = "select ReceiveTime,Description,Process from records where ";
		for(String str:keys){
//			sql += "Description like \'%" + str +"%\' or Description like '%��·����%'"
		}
	}
	public void insertRecords(String[] records){
		String[] fields = null;
		for(String str:records){
			fields = str.replace("\"","\'").split("\\|");
			if(fields.length != 11){
				logger.info("[INFO]"+Time.now()+"-�ü�¼�ֶ���Ŀ����11: "+str);
			}else{
				if(stmt != null){
					try {
						String delims = "\""+","+"\"";
						String sql="insert into records (sheetId,Resource,ReportMan,Description," +
								"ReceiveTime,Process,Reason,Influence,Restoration,Remarks,Id)" +
								" values("+Integer.valueOf(fields[0])+","+"\""+
								fields[1]+delims+fields[2]+delims+fields[3]+delims+
								fields[4]+delims+fields[5]+delims+fields[6]+delims+
								fields[7]+delims+fields[8]+delims+fields[9]+delims+
								fields[10]+"\""+");";
						logger.info("[INFO]"+Time.now()+" SQL Statement:"+sql);
						stmt.executeUpdate(sql);
						logger.info("[INFO]getUpdateCount:"+stmt.getUpdateCount());
					} catch (SQLException e) {
						logger.info("[INFO]"+Time.now()+" ��Ӽ�¼�����쳣��");
						logger.info("[INFO]"+Time.now()+" SQLException: "+e.getMessage());
						logger.info("[INFO]"+Time.now()+" SQLState: "+e.getSQLState());
						logger.info("[INFO]"+Time.now()+" VendorError: "+e.getErrorCode());
					}finally{
						
					}
				}
			}
		}
	}
	Connection conn;
	Statement stmt;
	static Logger logger =Logger.getLogger("com.yping.dao.LoadDriver");
}
