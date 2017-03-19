package com.appstore.issuance.system.utils;

import java.util.List;

import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.TerminalFactory;

public class ListNFCReaders {

	public static void main(String[] args) throws CardException {
		TerminalFactory factory = TerminalFactory.getDefault();
		List<CardTerminal> terminals = null;
		terminals = factory.terminals().list();
		for (CardTerminal temp : terminals) {
			System.out.println(temp);
		}
	}
}
