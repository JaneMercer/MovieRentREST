package company.movierental.test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import company.movierental.database.model.Manager;
import company.movierental.database.model.Movie;
import company.movierental.json.JsonHandler;
import company.movierental.json.MovieHandler;
import company.movierental.omdb.MovieFetcher;
import company.movierental.service.MoviePrice;
import company.movierental.service.InvoiceService;
import company.movierental.utils.ManagerSecurityKey;

@SuppressWarnings("unused")
public class Testing {

	public static List<Movie> testFetch() {
		List<Movie> movieList = MovieFetcher.getMoviesBySearch("A cat", 1, 2022, "794669bb");
		if (movieList != null) {
			for (Movie movie : movieList) {
				System.out.print(movie.toString());
				System.out.print("\n __________ \n");

			}
			MovieHandler dataFile = new MovieHandler("src\\main\\java\\JSON_database\\2.json");
			dataFile.addMovies(movieList);
		}

		return movieList;

	}

	public static void createDataBaseJSON() {
		try {
			ArrayList<Manager> databaseManagers = new ArrayList<>();
			// this method of DataBase access key generation is used only for quick
			// demonstration purposes
			ManagerSecurityKey uniqueKey = new ManagerSecurityKey();
			uniqueKey.setManagerKey("totallyUniqueKey");

			databaseManagers.add(new Manager("admin", "admin", uniqueKey));
			JsonHandler.createJsonFile("localDatabaseJSON", "src\\main\\java\\JSON_database\\", databaseManagers);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		testFetch();
		//createDataBaseJSON();

	}
}
