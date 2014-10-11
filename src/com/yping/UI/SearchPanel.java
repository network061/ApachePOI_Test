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
 * ����һ����ǩ��һ���ı�������һ����ť
 * @author ��ƽ��
 * update 20140815  ������־��¼�û�����Ĳ��ҹؼ���
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
		add(new JLabel("��������ҹؼ���:"));
		searchField = new JTextField();
		add(searchField);
		searchButton = new JButton("����������Ϣ");
		searchButton.addActionListener(new searchAction());
		add(searchButton);
		searchLabel = new JLabel();
		add(searchLabel);
	}
	class searchAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String regex = ",|��| |\\s+|��";
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
	JLabel searchLabel;		//��ʾ����ƥ������
	JTextField searchField;
	TDCSTable tdcsTable;
	Files data;
	static Logger logger =Logger.getLogger("com.yping.UI.SearchPanel");
}
