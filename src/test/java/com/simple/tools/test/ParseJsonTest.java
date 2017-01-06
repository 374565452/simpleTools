package com.simple.tools.test;

import org.junit.Test;

import com.simple.tools.utils.JsonUtils;

public class ParseJsonTest {

	@Test
	public void parseJson2JavaBean() {
		String json = "{\"name\":\"a1\"," + "\"password\":\"11\",\"students\":" + "[{\"name\":\"a2\","
				+ "\"password\":\"12\",\"students\":" + "null,\"age\":12,\"going\":" + "false,\"maps\":null},{"
				+ "\"name\":\"a3\"," + "\"password\":\"13\",\"students\"" + ":null,\"age\":13,\"going\":true,"
				+ "\"maps\":null}],\"age\":" + "10,\"going\":true,\"maps\":{\"k1\":" + "{\"name\":\"a4\",\""
				+ "password\":\"14\",\"students\":null,\"age\":" + "14,\"going\":true,\"maps\":"
				+ "null},\"k2\":{\"name\":\"a5\",\"password\":\"53\",\"students\":null,\"age\":15,\"going\":"
				+ "true,\"maps\":null}}}";
		String parseBeanStr=JsonUtils.getJavaFromJson("", "", json);
		System.out.println(parseBeanStr);
	}
}
