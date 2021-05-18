package app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:coach.properties")
public class CoachConfig {

	@Bean
	public SupplementService addSupplementService() {
		return new HealthySupplementService();
	}
	
	@Bean
	public Coach boxingCoach() {
		BoxingCoach coach = new BoxingCoach(addSupplementService());
		
		return coach;
	}
}
