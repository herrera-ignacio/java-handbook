package com.irh.annotations;

import org.springframework.stereotype.Component;

@Component("theSecretSupplementService")
public class ArtificialSupplementService implements SupplementService {

	@Override
	public String getSupplement() {
		return "Anabolics";
	}
	
	@Override
	public int getQuantity() {
		return 2;
	}
}
