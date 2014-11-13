package com.yping.UI.reports;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import org.apache.log4j.Logger;

public class ReportsTree extends JTree {
	public ReportsTree(){
		super();
	}
	public ReportsTree(DefaultMutableTreeNode rootNode,String dataPath,ReportsTextPane reportPane){
		super(rootNode);
		this.dataPath = dataPath;
		this.reportPane = reportPane;
		getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		addTreeSelectionListener(new selectionAction());
	}

	class selectionAction implements TreeSelectionListener{

		@Override
		public void valueChanged(TreeSelectionEvent e) {
			DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)getLastSelectedPathComponent();
			if(selectedNode != null && selectedNode.toString().contains(".doc")){
				if(selectedNode.isLeaf()){
					String path = dataPath.concat(selectedNode.toString().replace(".doc",".dat"));
					System.out.println(path);
					reportPane.removeCurrentDoc();
					reportPane.displayDoc(path);
				}
			}
		}
	}
	private String dataPath ;
	private ReportsTextPane reportPane;
	static Logger logger =Logger.getLogger("com.yping.UI.ReportsTree");
}
