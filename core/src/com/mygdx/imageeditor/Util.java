package com.mygdx.imageeditor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;

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
	
	public static byte[] intToSignedBytes(int value) {
		byte[] result = new byte[4];
		result[0] = (byte) (value >> 24);
		result[1] = (byte) ((value << 8) >> 24);
		result[2] = (byte) ((value << 16) >> 24);
		result[3] = (byte) ((value << 24) >> 24);
		return result;
	}
	
	public static void testIntToSignedBytes() {
		byte[] testResults = intToSignedBytes(543152314);
		int[] expectedResults = {32, 95,-40,-70};
		for(int i = 0; i < testResults.length; i++) {
			if((int) testResults[i] != expectedResults[i]) System.out.println("TEST FAILED! INDEX " + i + " IS " + testResults[i] + " EXPECTED: " + expectedResults[i]);
		}
	}
	
	public static Pixmap scalePixmap(Pixmap map, Vector2 desiredSize) {
		int tempColor;
		Pixmap newMap = new Pixmap((int) desiredSize.x, (int) desiredSize.y, Pixmap.Format.RGBA8888);
		float targetWidth = newMap.getWidth();
		float targetHeight = newMap.getHeight();
		float sourceWidth = map.getWidth();
		float sourceHeight = map.getHeight();
		for (int targetX = 0; targetX < newMap.getWidth(); targetX++) {
			for (int targetY = 0; targetY < newMap.getHeight(); targetY++) {
				int sourceX = Math.round(targetX / targetWidth * sourceWidth);
				int sourceY = Math.round(targetY / targetHeight * sourceHeight);
				tempColor = map.getPixel(sourceX, sourceY);
				
				newMap.drawPixel(targetX, targetY, tempColor);
			}
		}
		return newMap;
		}

}
