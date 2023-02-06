package company.movierental.test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import company.movierental.database.DatabaseFileController;
import company.movierental.database.model.Manager;
import company.movierental.database.model.Movie;
import company.movierental.omdb.MovieFetcher;
import company.movierental.service.MoviePrice;
import company.movierental.service.RentedMovieController;
import company.movierental.utils.security.ManagerSecurityKey;

@SuppressWarnings("unused")
public class Testing {

	public static List<Movie> testFetch() {
		List<Movie> movieList = MovieFetcher.getMoviesBySearch("The god", 1, 2022, "794669bb");
		if (movieList != null) {
			for (Movie movie : movieList) {
				System.out.print(movie.toString());
				System.out.print("\n __________ \n");

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
			DatabaseFileController.createJsonFile("localDatabaseJSON", "src\\main\\java\\JSON_database\\", databaseManagers);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		//testFetch();
		createDataBaseJSON();

//		List<Movie> movieList = testFetch();
//		
//		int i = 0;
//		for (Movie movie : movieList) {
//			// System.out.print(new MoviePrice(movie).getPricePerWeek());
//			LocalDate nowDate = LocalDate.now();
//			System.out.print("\n" + movie.getTitle() + ": "
//					+ RentedMovieController.calculateRentalPrice(movie, nowDate, nowDate.plusWeeks(i)) + "\n");
//			i += 1;
//
//		}
	}
}
