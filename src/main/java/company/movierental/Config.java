package company.movierental;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import company.movierental.json.MovieHandler;
import company.movierental.json.UserHandler;

@Configuration
public class Config {
	public static String FILE_PATH;

	@Bean
	public MovieHandler movieHandler() {
		return new MovieHandler(FILE_PATH);
	}
	@Bean
	public UserHandler userHandler() {
		return new UserHandler(FILE_PATH);
	}
}
