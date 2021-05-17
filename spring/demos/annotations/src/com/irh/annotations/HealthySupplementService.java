package com.irh.annotations;

import org.springframework.stereotype.Component;

@Component
public class HealthySupplementService implements SupplementService {
	
	@Override
	public String getSupplement() {
		return "Water";
	}
	
	@Override
	public int getQuantity() {
		return 1;
	}
}
