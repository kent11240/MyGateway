package com.mgw.util;

import java.util.UUID;

public final class UUIDUtility {
	public static String getRndUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public static String getRndUUID(int length) {
		String uuid = getRndUUID();
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			int rnd = (int) Math.floor(Math.random() * 32);
			sb.append(uuid.charAt(rnd));
		}
		return sb.toString();
	}
}
