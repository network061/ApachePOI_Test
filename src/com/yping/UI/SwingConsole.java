package com.yping.UI;

import javax.swing.SwingUtilities;

import com.yping.UI.search.SearchFrame;

public class SwingConsole {
	public static void run(){
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				SearchFrame gui = new SearchFrame();	
			}
		});
	}
}
