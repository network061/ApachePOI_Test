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
		add(new SearchMenu("TDCS excel data",data.getLogPath(),data.getXlsPath(),data.getXlsDatasPath(),
				data.getResultPath()));
		add(new TermsMenu("TDCS/CTC keyword",data.getTermsDoc().getPath(),words));
		add(new ReportsMenu("Report doc/pdf",data.getReportsPath(),data.getLogPath()));
	}
	
	Files data;
    InfoRetrieval IR;
	String[] words; //JList锟斤拷莩锟绞硷拷锟�
}
