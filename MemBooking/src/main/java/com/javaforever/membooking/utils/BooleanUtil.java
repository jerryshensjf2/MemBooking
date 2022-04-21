package com.javaforever.membooking.utils;

public class BooleanUtil {

	public static Boolean parseBoolean(String value){
		if ("true".equalsIgnoreCase(value)) return true;
		else if ("false".equalsIgnoreCase(value)) return false;
		else if ("Y".equalsIgnoreCase(value)) return true;
		else if ("N".equalsIgnoreCase(value)) return false;
		else if ("T".equalsIgnoreCase(value)) return true;
		else if ("F".equalsIgnoreCase(value)) return false;
		else if ("yes".equalsIgnoreCase(value)) return true;
		else if ("no".equalsIgnoreCase(value)) return false;
		else if ("1".equalsIgnoreCase(value)) return true;
		else if ("0".equalsIgnoreCase(value)) return false;
		else return null;
	}
}
