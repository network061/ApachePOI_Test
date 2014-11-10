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
 * 包含一个JTree组件、一个JEditPane
 * @author 杨平
 *
 */
public class ReportsFrame extends JFrame {
	public ReportsFrame(){
		DOMConfigurator.configure("./loggerConfig/ReportsFramelLogger.xml");
		//初始化数据类
		data = new Files();
		
		//设置网格布局
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		addComponents();
		
		setSize(960,480);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setTitle("分析报告　 by 杨平");
		setVisible(false);
	}
	public void addComponents(){
		//初始化各swing组件
		editPane = new JEditorPane();
		
		parent = new DefaultMutableTreeNode("分析报告");
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
			logger.info("父亲结点--"+f.getName());
			File[] fileArray = f.listFiles();
			if(fileArray != null){
				for(File file:fileArray){
					DefaultMutableTreeNode child = new DefaultMutableTreeNode(file.getName());
					parent.add(child);
					logger.info("增加结点--"+file.getName());
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
