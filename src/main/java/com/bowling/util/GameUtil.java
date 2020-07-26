package com.bowling.util;

public class GameUtil {

	
	public static String getHeader(int maxLengthName) {
		StringBuilder sbHeader = new StringBuilder();
		if(maxLengthName > 8) {
			sbHeader.append("Frame  ").append(addSpace(maxLengthName-5));
		}else {
			sbHeader.append("Frame     ");
		}
		sbHeader.append(addTabNumber(10));
		return sbHeader.toString();
	}
	public static String addSpace(int n) {
		StringBuilder sbSpace = new StringBuilder();
		for(int i=0;i<n;i++) {
			sbSpace.append(" ");
		}
		return sbSpace.toString();
	}
	private static String addTabNumber(int n) {
		StringBuilder sbTabNumber = new StringBuilder();
		for(int i=1;i<=n;i++) {
			sbTabNumber.append(i).append("     ");
		}
		return sbTabNumber.toString();
	}
}
