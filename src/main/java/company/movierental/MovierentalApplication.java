package company.movierental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MovierentalApplication {

	public static void main(String[] args) {
		if (args.length > 0) {
			//"src\\main\\java\\JSON_database\\localDatabaseJSON.json"
			Config.FILE_PATH = args[0];
		}
		SpringApplication.run(MovierentalApplication.class, args);
	}
	
}
