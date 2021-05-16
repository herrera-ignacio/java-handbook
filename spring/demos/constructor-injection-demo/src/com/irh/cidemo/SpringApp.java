package com.irh.cidemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApp {

		public static void main(String[] args) {
			
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			
			Coach theCoach = context.getBean("myCoach", Coach.class);
			
			System.out.println(theCoach.getDailyWorkout());

			System.out.println(theCoach.getDailyFortune());
			
			context.close();
		}
}
