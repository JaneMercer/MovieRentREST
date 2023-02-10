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

/**
 * The StartingPoint class contains methods to create a JSON database and fetch
 * movie information from OMDB.
 * 
 * @author Iryna
 * @version 0.1
 * @since 10.02.23
 */
public class StartingPoint {

	/**
	 * Fetches a list of movies from OMDB and adds them to a JSON database.
	 */
	public static void fetchToDatabaseFromOMDB() {
		// Fetch a list of movies from OMDB using the MovieFetcher class
		List<Movie> movieList = MovieFetcher.getMoviesBySearch("best", 1, 2020, "794669bb");
		if (movieList != null) {

			// Print out each movie from the list
			for (Movie movie : movieList) {
				System.out.print(movie.toString());
				System.out.print("\n __________ \n");
			}

			MovieHandler dataFile = new MovieHandler("src\\main\\java\\JSON_database\\2.json");
			// Add movie to the JSON database
			for (Movie movie : movieList) {
				dataFile.addMovie(movie);
			}
		}

	}

	/**
	 * Creates a JSON database for managing movie information.
	 */
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

	/**
	 * Main method that calls the createDataBaseJSON() and fetchToDatabaseFromOMDB()
	 * methods.
	 * 
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		createDataBaseJSON();
		fetchToDatabaseFromOMDB();

	}
}
