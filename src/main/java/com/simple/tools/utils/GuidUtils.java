package com.simple.tools.utils;

import java.util.UUID;

public class GuidUtils {

	public static String getGuid(){
		UUID uuid=UUID.randomUUID();
		return uuid.toString();
	}
	
}
