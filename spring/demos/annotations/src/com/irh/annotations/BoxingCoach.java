package com.irh.annotations;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Component
public class BoxingCoach implements Coach {
	
	@Autowired
	@Qualifier("theSecretSupplementService")
	private SupplementService supplementService;
	
	@Override
	public String getDailyWorkout() {
		return "[BoxingCoach] Hit the bag!";
	}
	
	@Override
	public String getDailySupplement() {
		return "[BoxingCoach] Today supplement is " + String.valueOf(supplementService.getQuantity()) + " " + supplementService.getSupplement();
	}	
}
