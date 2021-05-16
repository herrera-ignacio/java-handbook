package com.irh.setterinjection;

public class BaseballCoach implements Coach {

	// Private field for dependency to be injected
	private FortuneService fortuneService;
	private String workoutLength;
	private String name;
	
	// Must create no-arg constructor
	public BaseballCoach() {
		System.out.println("BaseballCoach: no-arg constructor");
	}
	
	// Setter for injection
	public void setFortuneService(FortuneService fortuneService) {
		System.out.println("[BaseballCoach] setFortuneService");
		this.fortuneService = fortuneService;
	}
	
	public void setWorkoutLength(String length) {
		System.out.println("[BaseballCoach] setWorkoutLength");
		this.workoutLength = length;
	}
	
	public void setName(String name) {
		System.out.println("[BaseballCoach] setName");
		this.name = name;
		
	}
	
	@Override
	public String getDailyWorkout() {
		return this.name + ": Spend " + this.workoutLength + " on batting practice";
	}
	
	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}
}
