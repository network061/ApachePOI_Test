package com.yping.UI;

import javax.swing.SwingUtilities;

public class SwingConsole {
	public static void run(){
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				SimpleGUI gui = new SimpleGUI();	
			}
		});
	}
}
