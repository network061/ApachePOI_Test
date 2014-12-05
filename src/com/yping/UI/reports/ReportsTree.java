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
	public ReportsTree(DefaultMutableTreeNode rootNode,String DocDatPath,String PdfDatPath,ReportsTextPane reportPane){
		super(rootNode);
		this.DocDatPath = DocDatPath;
		this.PdfDatPath = PdfDatPath;
		this.reportPane = reportPane;
		getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		addTreeSelectionListener(new selectionAction());
	}

	class selectionAction implements TreeSelectionListener{

		@Override
		public void valueChanged(TreeSelectionEvent e) {
			DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)getLastSelectedPathComponent();
			if(selectedNode != null && selectedNode.isLeaf() ){
				String nodeName = selectedNode.toString();
				reportPane.removeCurrentDoc();
				if( nodeName.contains(".doc") ){
					reportPane.displayReport( DocDatPath.concat(nodeName.replace(".doc",".dat")));
				}else if(nodeName.contains(".pdf")){
					reportPane.displayReport( PdfDatPath.concat(nodeName.replace(".pdf",".dat")));
				}
			}
		}
	}
	private String DocDatPath ; //doc dat file path
	private String PdfDatPath ; //pdf dat file path
	private ReportsTextPane reportPane;
	static Logger logger =Logger.getLogger("com.yping.UI.ReportsTree");
}
