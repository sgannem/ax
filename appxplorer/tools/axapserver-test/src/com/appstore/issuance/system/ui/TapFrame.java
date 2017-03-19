package com.appstore.issuance.system.ui;

import javax.swing.JFrame;

public class TapFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TapFrame(String title) {
		super(title);
		System.out.println("welcome to appstore");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 900);
		setVisible(true);
	}
	

}
