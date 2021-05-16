package com.irh.cidemo;

public class BaseballCoach implements Coach {

	// Private field for dependency to be injected
	private FortuneService fortuneService;
	
	// Constructor for dependency injection
	public BaseballCoach(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}
	
	@Override
	public String getDailyWorkout() {
		return "Spend 30 minutes on batting practice";
	}
	
	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}
}
