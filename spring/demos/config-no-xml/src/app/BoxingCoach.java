package app;

import org.springframework.beans.factory.annotation.Value;

public class BoxingCoach implements Coach {

	private SupplementService supplementService;
	
	@Value("${name}")
	private String name;
	
	public BoxingCoach(SupplementService ss) {
		this.supplementService = ss;
	}
	
	@Override
	public String getDailyWorkout() {
		return "[BoxingCoach] " + this.name + ": Hit the bag!";
	}
	
	@Override
	public String getDailySupplement() {
		return "[BoxingCoach] " + this.name + ": Have " + this.supplementService.getQuantity() + " " + this.supplementService.getSupplement();
	}
}
