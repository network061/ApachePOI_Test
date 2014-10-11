package com.yping.UI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.yping.classes.TdcsTask;
import com.yping.util.Files;
import com.yping.util.Time;
/**
 * 包括一个标签、一个文本输入框和一个按钮
 * @author 楊平　
 * update 20140815  新增日志记录用户输入的查找关键字
 *
 */
public class SearchPanel extends JPanel {
	public SearchPanel(Files data,TDCSTable tdcsTable){
		super(new GridLayout(1,2));
		DOMConfigurator.configure("./loggerConfig/SearchPanelLogger.xml");
		this.tdcsTable = tdcsTable;
		this.data = data;	
	}
	public void addItem(){
		add(new JLabel("请输入查找关键字:"));
		searchField = new JTextField();
		add(searchField);
		searchButton = new JButton("搜索不良信息");
		searchButton.addActionListener(new searchAction());
		add(searchButton);
		searchLabel = new JLabel();
		add(searchLabel);
	}
	class searchAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String regex = ",|，| |\\s+|　";
			String[] keys= searchField.getText().toString().trim().split(regex);
			TdcsTask task = new TdcsTask();
			logger.info(Time.now()+" search:"+Arrays.toString(keys));
			
			String[] result = null;
			result = task.doSearchV2(keys,data.listResultFiles());
			searchLabel.setText("About " + result.length + " results.");
			logger.info("About " + result.length + " results.");
			if(result.length != 0){
				tdcsTable.update(result);
			}	
		}
	}
	
	JButton searchButton;
	JLabel searchLabel;		//显示查找匹配数量
	JTextField searchField;
	TDCSTable tdcsTable;
	Files data;
	static Logger logger =Logger.getLogger("com.yping.UI.SearchPanel");
}
