package com.irh.iocdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApp {
	
	public static void main(String[] args) {
		
		// Load configuration file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		// Retrieve bean from spring container
		Coach theCoach = context.getBean("myCoach", Coach.class);
		
		System.out.println(theCoach.getDailyWorkout());
		
		context.close();
;	}
}
