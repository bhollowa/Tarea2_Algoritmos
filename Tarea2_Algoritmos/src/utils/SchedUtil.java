package utils;

import java.util.ArrayList;

public class SchedUtil {

	public static int getMaxBinaryInt(int a, int b) {
		int length = a*b;
		StringBuilder ret = new StringBuilder();
		
		for (int i = 0; i < length; i++) {
			ret.append("1");
		}
		
		return Integer.parseInt(ret.toString(), 2);
	}

	public static int nbOfOnes(String arg) {
		int count = 0;
		
		for (int j = 0; j < arg.length(); j++) {
			if(arg.charAt(j) == '1') {
				count++;
			}
		}
		
		return count;
	}

	public static String addPrefix(String binaryString, int size) {
		String ret = binaryString;
		
		for (int i = 0; i < size - binaryString.length(); i++) {
			ret = "0".concat(ret);
		}
		
		return ret;
	}

	public static boolean validConfig(ArrayList<String> configuration) {
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i < configuration.get(0).length(); i++) {
			String charsum = "0";
			
			for (String each : configuration) {
				charsum = "" +(Integer.parseInt(each.substring(i, i+1)) + Integer.parseInt(charsum));
			}
			
			builder.append(charsum);
		}
		
		String result = builder.toString();
		
		for (int i = 0; i < configuration.get(0).length(); i++) {
			if (result.charAt(i) != '1')
				return false;
		}
		
		return true;
	}
}
