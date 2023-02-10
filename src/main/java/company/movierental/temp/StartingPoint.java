package company.movierental.temp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import company.movierental.database.handlers.JsonHandler;
import company.movierental.database.handlers.MovieHandler;
import company.movierental.database.model.Manager;
import company.movierental.database.model.Movie;
import company.movierental.utils.genkey.ManagerSecurityKey;
import company.movierental.utils.omdb.MovieFetcher;

public class StartingPoint {

	public static List<Movie> fetchToDatabaseFromOMDB() {
		List<Movie> movieList = MovieFetcher.getMoviesBySearch("best", 1, 2020, "794669bb");
		if (movieList != null) {
			for (Movie movie : movieList) {
				System.out.print(movie.toString());
				System.out.print("\n __________ \n");

			}
			MovieHandler dataFile = new MovieHandler("src\\main\\java\\JSON_database\\2.json");
			for (Movie movie : movieList) {
				dataFile.addMovie(movie);
			}
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
			JsonHandler.createJsonFile("2", "src\\main\\java\\JSON_database\\", databaseManagers);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		createDataBaseJSON();
		fetchToDatabaseFromOMDB();

	}
}
