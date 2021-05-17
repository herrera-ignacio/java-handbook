package com.irh.annotations;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApp {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		// Specific bean identifier example
		Coach theSpecificCoach = context.getBean("theCoach", Coach.class);
		
		// Default bean identifier example
		Coach theDefaultCoach = context.getBean("yogaCoach", Coach.class);
		
		// Default bean identifier example
		Coach theBoxingCoach = context.getBean("boxingCoach", Coach.class);
		
		
		// TennisCoach: Constructor injection & method injection example
		System.out.println(theSpecificCoach.getDailyWorkout());
		System.out.println(theSpecificCoach.getDailySupplement());

		// YogaCoach: Setter injection example
		System.out.println(theDefaultCoach.getDailyWorkout());
		System.out.println(theDefaultCoach.getDailySupplement());
		
		// BoxingCoach: Field injection example and qualifier
		System.out.println(theBoxingCoach.getDailyWorkout());
		System.out.println(theBoxingCoach.getDailySupplement());
		
		context.close();
	}
}
