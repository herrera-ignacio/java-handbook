package app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringApp {
	
	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CoachConfig.class);
		
		Coach coach = context.getBean("boxingCoach", Coach.class);
		
		System.out.println(coach.getDailyWorkout());
		System.out.println(coach.getDailySupplement());
		
		context.close();
	}
}
