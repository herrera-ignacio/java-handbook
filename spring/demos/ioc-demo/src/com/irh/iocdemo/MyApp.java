package com.irh.iocdemo;

public class MyApp {
	
	public static void main(String[] args) {
		Coach theCoach = new RunningCoach();
		
		System.out.println(theCoach.getDailyWorkout());
	}
}
