package com.yping.UI.reports;

import java.awt.GridBagLayout;
import java.io.File;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.yping.UI.GBC;
import com.yping.util.Files;
/**
 * ����һ��JTree�����һ��JEditPane
 * @author ��ƽ
 *
 */
public class ReportsFrame extends JFrame {
	public ReportsFrame(){
		DOMConfigurator.configure("./loggerConfig/ReportsFramelLogger.xml");
		//��ʼ��������
		data = new Files();
		
		//�������񲼾�
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		addComponents();
		
		setSize(960,480);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setTitle("�������桡 by ��ƽ");
		setVisible(false);
	}
	public void addComponents(){
		//��ʼ����swing���
		editPane = new JEditorPane();
		
		parent = new DefaultMutableTreeNode("��������");
		addNodes(new File(data.getReportsPath()),parent);
		if(parent!=null){
			tree = new JTree(parent);
		}else{
			tree = new JTree();
		}
		
		add(new JScrollPane(tree),new GBC(0,0,4,6).setFill(GBC.BOTH).setWeight(100,100));
		add(new JScrollPane(editPane),new GBC(5,0).setFill(GBC.BOTH).setWeight(100,100));
	}
	public void addNodes(File f,DefaultMutableTreeNode parent){
		if(f != null){
			logger.info("���׽��--"+f.getName());
			File[] fileArray = f.listFiles();
			if(fileArray != null){
				for(File file:fileArray){
					DefaultMutableTreeNode child = new DefaultMutableTreeNode(file.getName());
					parent.add(child);
					logger.info("���ӽ��--"+file.getName());
					if(f.isDirectory()){
						addNodes(file,child);
					}
				}	
			}
		}
	}
	Files data;
	DefaultMutableTreeNode parent;
	JTree tree;
	JEditorPane editPane;
	static Logger logger =Logger.getLogger("com.yping.UI.ReportsFrame");
}
