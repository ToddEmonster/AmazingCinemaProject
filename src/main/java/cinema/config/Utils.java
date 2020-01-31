package cinema.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Utils {
	/***
	 * Une classe de configuration pour dire à SpringBoot qu'il peut utiliser ModelMapper depuis l'extérieur
	 */
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
}
