package com.yping.UI.reports;

import java.io.File;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import com.yping.util.Files;

public class ReportsTree extends JTree {
	public ReportsTree(){
		data = new Files();
		parent = null;
		child = null;
		rootPath = data.getReportsPath();
		parent = new DefaultMutableTreeNode("·ÖÎö±¨¸æ");
		//addNodes(new File(rootPath),parent,child);
	}
	
	Files data;
	DefaultMutableTreeNode parent;
	DefaultMutableTreeNode child;
	String rootPath;
}
