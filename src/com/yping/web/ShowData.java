package com.yping.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.yping.classes.TdcsTask;

public class ShowData extends HttpServlet{
	private static Logger logger = Logger.getLogger("com.yping.web.ShowData");
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		String configFile=getInitParameter("log4j-conf");
		DOMConfigurator.configureAndWatch(configFile);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	    resp.setContentType("text/html; charset=gb2312");  
	    logger.debug(req.getRemoteAddr());
	    PrintWriter out = resp.getWriter();
		//根据请求参数读取result目录下数据
		String month = req.getParameter("month");	
		String year = req.getParameter("year");
		String[] selectedIndexes = req.getParameterValues("selectIndexes");
		
		int[] selected = null;
		if(null != selectedIndexes){
			System.out.println("SelectedIndexes: "+selectedIndexes.length);
			selected = new int[selectedIndexes.length];
			for(int index =0 ;index<selectedIndexes.length;++index){
				selected[index] = Integer.valueOf(selectedIndexes[index]);
				logger.info(selected[index]);
			}
			System.out.println();
		}else{
			selected = new int[]{1,2,3,4};
		}
				
		TdcsTask analyze = null;
		Scanner in = null;
		//通过servletContext读取result目录路径配置
		ServletContext context = getServletContext();
		String resultDir = context.getInitParameter(("XLS_RESULTS_DIRECTORY"));
        
		String fileName = null;
		for(File aFile:new File(resultDir.concat(year + File.separator)).listFiles()){
			fileName = aFile.getName();
			
			if(year.equals(fileName.substring(0,fileName.indexOf("年"))) && month.equals(fileName.substring(fileName.indexOf("年")+1,fileName.indexOf("月")))){
				try {
					in = new Scanner(new FileReader(aFile));
				} catch (FileNotFoundException e) {
					// 读取result目录下文件失败
					e.printStackTrace();
				}
				analyze = new TdcsTask(in);
			}
		}
		if(null != analyze){
			System.out.println("服务器端已读取选择年份数据: "+year+"年");
			System.out.println("服务器端已读取选择月份数据: "+month+"月");
			String[] lines = analyze.getWebData();
			req.setAttribute("year",year);
			req.setAttribute("month",month);
			req.setAttribute("selectedIndexes",selected);
			req.setAttribute("datas",lines);
			RequestDispatcher view = req.getRequestDispatcher("result.jsp");
			view.forward(req,resp);
		}else{
			System.out.println(year+"年"+month+"月数据为空");
		}	
	}
}
