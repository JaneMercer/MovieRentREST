package company.movierental.omdb;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import company.movierental.database.model.Movie;
import company.movierental.json.JsonHelper;

public class MovieFetcher {

	/**
	 * Returns the movie information for a given imdbID and API key.
	 *
	 * @param imdbID the imdbID of the movie to retrieve information about
	 * @param apikey the API key for the OMDB API
	 * @return the movie info type Movie for the given imdbID
	 */
	public static Movie getMovieById(String imdbID, String apikey) {
		try {
			JsonObject jsonObject = JsonHelper
					.fetchJsonObject("http://www.omdbapi.com/?i=" + imdbID + "&apikey=" + apikey);
			return JsonHelper.createMovieFromJson(jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error caught while fetching movie information for imdbID: " + imdbID);
			return null;
		}
	}

	/**
	 * Returns the imdbID list of movies that match the given search.
	 *
	 * @param searchRequest the string used to search for movies
	 * @param pageNumber    the search results page that is to be returned
	 * @param releaseYear   the year of release of the movies to search for
	 * @param apikey        the API key for the OMDB API
	 * @return a list of imdbIDs for movies that match the given search
	 */
	public static List<String> getImdbIDsBySearch(String searchRequest, Integer pageNumber, Integer releaseYear,
			String apikey) {
		List<String> imdbIDs = new ArrayList<>();
		try {
			JsonObject jsonObject = JsonHelper.fetchJsonObject("http://www.omdbapi.com/?s=" + searchRequest
					+ "&page=" + pageNumber + "&type=movie&r=json&y=" + releaseYear + "&apikey=" + apikey);
			JsonArray searchResults = jsonObject.getAsJsonArray("Search");
			if (searchResults != null) {
				for (int i = 0; i < searchResults.size(); i++) {
					JsonObject result = searchResults.get(i).getAsJsonObject();
					imdbIDs.add(result.get("imdbID").getAsString());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imdbIDs;
	}

	/**
	 * Returns a list of movies based on the given search request.
	 *
	 * @param searchRequest the string used to search for movies
	 * @param pageNumber    the search results page that is to be returned
	 * @param releaseYear   the year of release of the movies to search for
	 * @param apikey        the API key for the OMDB API
	 *
	 * @return a list of Movie objects
	 */
	public static List<Movie> getMoviesBySearch(String searchRequest, Integer pageNumber, Integer releaseYear,
			String apikey) {
		List<Movie> movieList = new ArrayList<>();
		List<String> imdbIDs = getImdbIDsBySearch(searchRequest, pageNumber, releaseYear, apikey);

		if (!imdbIDs.isEmpty()) {
			for (String imdbID : imdbIDs) {
				Movie fetchedMovie = getMovieById(imdbID, apikey);
				if (fetchedMovie != null) {
					movieList.add(fetchedMovie);
				}
			}
		}

		return movieList;
	}

}
