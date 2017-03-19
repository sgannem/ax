package com.appstore.issuance.system.ui;

import com.appstore.issuance.system.utils.Utils;

public class TestArray {

	static int counter = 0;

	static byte[][] cardStateAPDUs = { Utils.getHexString("\220\000".getBytes()).getBytes(), Utils.getHexString("\004\001\001\022\000\032\005\221\257".getBytes()).getBytes(),
			Utils.getHexString("\004\001\001\002\001\032\005\221\257".getBytes()).getBytes(),
			Utils.getHexString("\000\000\000\000\000\000\000\377\377\377\360\'a'#\025\221\000".getBytes()).getBytes(), 
			Utils.getHexString("\326\241x\221\000".getBytes()).getBytes(),
			Utils.getHexString("@\034\000\221\000".getBytes()).getBytes() };

	public static void takeBytes(byte[] bytes) {
		System.out.println(bytes.length);
		for (int i = 0; i < bytes.length; i++) {
			System.out.print(bytes[i]);
		}
		System.out.println();
	}

	public static void readApdus() {
		byte[] b = new byte[cardStateAPDUs[counter].length];
		for (int i = 0; i < cardStateAPDUs[counter].length; i++) {
			b[i] = cardStateAPDUs[counter][i];
		}
		takeBytes(b);
		counter++;
	}

	public static void main(String[] args) {
		int size = 6, i=0;
		while (i < size) {
			readApdus();
			i++;
		}
		
//		takeBytes(Utils.getHexString("\220\000".getBytes()).getBytes());
//		System.out.println();
	}

}