package com.yping.UI;

import javax.swing.JMenuBar;
import com.yping.UI.reports.ReportsMenu;
import com.yping.UI.search.SearchMenu;
import com.yping.UI.terms.TermsMenu;
import com.yping.classes.InfoRetrieval;
import com.yping.util.Files;


public class TDCSMenuBar extends JMenuBar{
	public TDCSMenuBar(Files data){	
		this.data = data;
		IR = new InfoRetrieval(data.getTermsDoc(),data.listResultFiles());
	    words = IR.getTerms();
		
		addMenuItem();
	}
	public void addMenuItem(){
		add(new SearchMenu("涓嶈壇淇℃伅鏁版嵁",data.getLogPath(),data.getXlsPath(),data.getXlsDatasPath(),
				data.getResultPath()));
		add(new TermsMenu("涓嶈壇淇℃伅鍏抽敭瀛�",data.getTermsDoc().getPath(),words));
		add(new ReportsMenu("鍒嗘瀽鎶ュ憡",data.getReportsPath(),data.getLogPath()));
	}
	
	Files data;
    InfoRetrieval IR;
	String[] words; //JList锟斤拷莩锟绞硷拷锟�
}
