package com.irh.annotations;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Component("theCoach")
public class TennisCoach implements Coach {
	
	private SupplementService supplementService;
	private int supplementQuantity;
	
	// Constructor injection with Qualifier
	@Autowired
	public TennisCoach(@Qualifier("theSecretSupplementService") SupplementService ss) {
		this.supplementService = ss;
	}
	
	// Method injection
	@Autowired
	@Qualifier("healthySupplementService")
	public void injectSupplementQuanitity(SupplementService ss) {
		this.supplementQuantity = ss.getQuantity();
	}
	
	
	@Override
	public String getDailyWorkout() {
		return "[TennisCoach] Practice tennis!";
	}
	
	@Override
	public String getDailySupplement() {
		return "[TennisCoach] Today supplement is " + String.valueOf(supplementQuantity) + " " + supplementService.getSupplement();
	}	
}
