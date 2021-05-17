package com.irh.annotations;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Component
public class YogaCoach implements Coach {
	
	private SupplementService supplementService;
	
	public YogaCoach() {
	}
	
	// Setter injection
	@Autowired
	@Qualifier("healthySupplementService")
	public void setFortuneService(SupplementService ss) {
		this.supplementService = ss;
	}
	
	@Override
	public String getDailyWorkout() {
		return "[YogaCoach] Practice Yoga!";
	}
	
	@Override
	public String getDailySupplement() {
		return "[YogaCoach] Today supplement is " + String.valueOf(supplementService.getQuantity()) + " " + supplementService.getSupplement();
	}	
}
