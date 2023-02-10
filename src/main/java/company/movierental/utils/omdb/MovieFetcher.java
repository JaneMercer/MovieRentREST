package company.movierental.utils.omdb;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import company.movierental.database.handlers.JsonHandler;
import company.movierental.database.model.Movie;
import company.movierental.database.model.Rating;

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
			JsonObject jsonObject = JsonHandler
					.fetchJsonObject("http://www.omdbapi.com/?i=" + imdbID + "&apikey=" + apikey);
			return createMovieFromOMDB(jsonObject);
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
			JsonObject jsonObject = JsonHandler.fetchJsonObject("http://www.omdbapi.com/?s=" + searchRequest + "&page="
					+ pageNumber + "&type=movie&r=json&y=" + releaseYear + "&apikey=" + apikey);
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

	public static Movie createMovieFromOMDB(JsonObject jsonObject) {

		DateTimeFormatter formatter = new DateTimeFormatterBuilder()
				// case insensitive to parse JAN and FEB
				.parseCaseInsensitive()
				// add pattern
				.appendPattern("dd MMM yyyy")
				// create formatter (use English Locale to parse month names)
				.toFormatter(Locale.ENGLISH);

		// create list of Ratings
		JsonArray ratings = jsonObject.getAsJsonArray("Ratings");
		List<Rating> movieRatings = new ArrayList<>();
		for (int i = 0; i < ratings.size(); i++) {
			JsonObject rating = ratings.get(i).getAsJsonObject();
			movieRatings.add(new Rating(rating.get("Source").getAsString(), rating.get("Value").getAsString()));
		}

		try {
			String title = jsonObject.get("Title").getAsString();
			String year = jsonObject.get("Year").getAsString();
			String rated = jsonObject.get("Rated").getAsString();
			String releaseDateJson = jsonObject.get("Released").getAsString();
			LocalDate releaseDate = null;
			if (!releaseDateJson.equals("N/A")) {
				releaseDate = LocalDate.parse(releaseDateJson, formatter);
			}
			String runtime = jsonObject.get("Runtime").getAsString();
			String genre = jsonObject.get("Genre").getAsString();
			String director = jsonObject.get("Director").getAsString();
			String writer = jsonObject.get("Writer").getAsString();
			String actors = jsonObject.get("Actors").getAsString();
			String plot = jsonObject.get("Plot").getAsString();
			String language = jsonObject.get("Language").getAsString();
			String country = jsonObject.get("Country").getAsString();
			String awards = jsonObject.get("Awards").getAsString();
			String poster = jsonObject.get("Poster").getAsString();
			String metascore = jsonObject.get("Metascore").getAsString();
			String imdbRating = jsonObject.get("imdbRating").getAsString();
			String imdbVotes = jsonObject.get("imdbVotes").getAsString();
			String imdbID = jsonObject.get("imdbID").getAsString();

			if (title == null || imdbRating == null || releaseDate == null) {
				throw new IllegalArgumentException(
						"Title, ImdbRating, and Released are required fields, cannot be null. Movie won't be created.");
			}

			return new Movie(title, year, rated, releaseDate, runtime, genre, director, writer, actors, plot, language,
					country, awards, poster, movieRatings, metascore, imdbRating, imdbVotes, imdbID);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Error parsing movie information from JsonObject", e);
		}
	}
}
