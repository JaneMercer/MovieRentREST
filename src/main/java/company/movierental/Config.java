package company.movierental;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import company.movierental.database.handlers.CategoryHandler;
import company.movierental.database.handlers.InvoiceHandler;
import company.movierental.database.handlers.MovieHandler;
import company.movierental.database.handlers.UserHandler;

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
	@Bean
	public InvoiceHandler invoiceHandler() {
		return new InvoiceHandler(FILE_PATH);
	}
	@Bean
	public CategoryHandler categoryHandler() {
		return new CategoryHandler(FILE_PATH);
	}
}
