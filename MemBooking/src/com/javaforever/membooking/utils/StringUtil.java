package com.javaforever.membooking.utils;
public class StringUtil {
	public static boolean isBlank(Object o) {
		if (o == null || "".equals(o))
			return true;
		else
			return false;
	}

	public static String nullTrim(String str) {
		return str == null || str.trim().length() == 0 || "null".equals(str) ? "" : str;
	}
}
