package com.mygdx.imageeditor;

public class Util {
	
	public static int bytesToInt(byte[] bytes) {
		int result = 0;
		int[] ints = unsignBytes(bytes);
		for (int i = 0; i < ints.length; i++) {
			result += ints[i] << (8 * i);
		}
		return result;
	}
	
	public static int[] unsignBytes(byte[] bytes) {
		int[] ints = new int[bytes.length];
		for(int i = 0; i < bytes.length; i++) {
			byte someByte = bytes[i];
			if (someByte >= 0) {
				ints[i] = someByte;
			}
			else {
				int distance = (someByte) - (-129);
				ints[i] = 127 + distance;
			}
		}
		return ints;
		}

}
