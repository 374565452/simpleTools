package com.simple.tools.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simple.tools.utils.JsonFormatUtils;

public class JsonFormatTest {

	@Test
	public void testJsonFormat() throws Exception{
		
		Student a1=new Student();
		a1.setAge(10);
		a1.setGoing(true);
		a1.setPassword("11");
		a1.setName("a1");
		
		Student a2=new Student();
		a2.setAge(12);
		a2.setGoing(false);
		a2.setPassword("12");
		a2.setName("a2");
		
		Student a3=new Student();
		a3.setAge(13);
		a3.setGoing(true);
		a3.setPassword("13");
		a3.setName("a3");
		
		
		Student a4=new Student();
		a4.setAge(14);
		a4.setGoing(true);
		a4.setPassword("14");
		a4.setName("a4");
		
		
		Student a5=new Student();
		a5.setAge(15);
		a5.setGoing(true);
		a5.setPassword("53");
		a5.setName("a5");
		
		List<Student> lists=new ArrayList<>();
		lists.add(a2);
		lists.add(a3);
		
		Map<String,Student> maps=new HashMap<>();
		maps.put("k1", a4);
		maps.put("k2", a5);
		
		a1.setStudents(lists);
		a1.setMaps(maps);
		
		 ObjectMapper mapper = new ObjectMapper(); 
		 String string = mapper.writeValueAsString(a1);
		 System.out.println("no format the value is ----"+string);
		 
		 String formatString=JsonFormatUtils.formatJson2(string);
		 System.out.println("after format the value is -------"+formatString);
	}
	
	
	class Student{
		private String name;
		
		private String password;
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public List<Student> getStudents() {
			return students;
		}

		public void setStudents(List<Student> students) {
			this.students = students;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public boolean isGoing() {
			return going;
		}

		public void setGoing(boolean going) {
			this.going = going;
		}

		public Map<String, Student> getMaps() {
			return maps;
		}

		public void setMaps(Map<String, Student> maps) {
			this.maps = maps;
		}

		private List<Student> students;
		
		private int age;
		
		private boolean going;
		
		private Map<String,Student> maps;
	}
	
}
