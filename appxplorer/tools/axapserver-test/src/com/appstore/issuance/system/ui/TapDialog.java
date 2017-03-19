package com.appstore.issuance.system.ui;

import java.awt.Dialog;

import javax.swing.JDialog;

public class TapDialog extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TapDialog(String title) {
		super();
		setTitle(title);
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
	}

}
