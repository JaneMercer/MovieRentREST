package company.movierental.json;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import company.movierental.database.model.Movie;
import company.movierental.database.model.Rating;

/**
 * The `MovieHandler` class extends the `JsonHandler` class to provide methods
 * for adding, updating and removing movies from a local JSON file.
 */
public class MovieHandler extends JsonHandler {
	/**
	 * Constructor that sets the file path to the local JSON file.
	 * 
	 * @param filePath The file path to the local JSON file.
	 */
	public MovieHandler(String filePath) {
		super(filePath);
	}

	/**
	 * Method to add a list of movies to the local JSON file.
	 *
	 * @param movieList The list of movies to be added.
	 * @return Returns `true` if the movies were added successfully and the database
	 *         was saved, `false` otherwise.
	 */
	public boolean addMovies(List<Movie> movieList) {
		if (this.database == null) {
			return false;
		}

		for (Movie movie : movieList) {
			if (!database.getMovies().stream()
					.anyMatch(existingMovie -> existingMovie.getImdbID().equals(movie.getImdbID()))) {
				this.database.getMovies().add(movie);
			}
		}
		this.database.setLastEditDate(LocalDateTime.now());
		return saveDatabase();
	}

	/**
	 * Method to update a movie in the local JSON file.
	 *
	 * @param updatedMovie The updated movie.
	 * @return Returns `true` if the movie was updated successfully and the database
	 *         was saved, `false` otherwise.
	 */
	public boolean updateMovie(Movie updatedMovie) {
		if (this.database == null) {
			return false;
		}
		List<Movie> movies = this.database.getMovies();
		for (int i = 0; i < movies.size(); i++) {
			if (movies.get(i).getImdbID().equals(updatedMovie.getImdbID())) {
				movies.set(i, updatedMovie);
				break;
			}
		}

		this.database.setLastEditDate(LocalDateTime.now());
		return saveDatabase();

	}

	/**
	 * Method to remove a movie from the local JSON file.
	 *
	 * @param imdbID The imdbID of the movie to be removed.
	 * @return Returns `true` if the movie was removed successfully and the database
	 *         was saved, `false` otherwise.
	 */
	public boolean removeMovie(String imdbID) {
		if (this.database == null) {
			return false;
		}
		Movie movieToRemove = null;
		for (Movie movie : this.database.getMovies()) {
			if (movie.getImdbID().equals(imdbID)) {
				movieToRemove = movie;
				break;
			}
		}

		if (movieToRemove != null) {
			this.database.getMovies().remove(movieToRemove);
			this.database.setLastEditDate(LocalDateTime.now());
			return saveDatabase();
		}
		return false;
	}

	/**
	 * 
	 * Retrieve a movie by its IMDb ID.
	 * 
	 * @param imdbID the IMDb ID of the movie to retrieve
	 * 
	 * @return a {@link Movie} object if a movie with the specified IMDb ID exists,
	 *         or {@code null} otherwise
	 */
	public Movie getMovieByImdbID(String imdbID) {
		if (this.database == null) {
			return null;
		}
		List<Movie> movies = this.database.getMovies();
		for (Movie movie : movies) {
			if (movie.getImdbID().equals(imdbID)) {
				return movie;
			}
		}
		return null;
	}

	public static Movie createMovieFromJson(JsonObject jsonObject) {

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
			int year = jsonObject.get("Year").getAsInt();
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

			if (title.equals("N/A") || imdbID.equals("N/A") || releaseDate == null) {
				throw new IllegalArgumentException(
						"Title, imdbID, and Released are required fields, cannot be null. Movie won't be created.");
			}

			return new Movie(title, year, rated, releaseDate, runtime, genre, director, writer, actors, plot, language,
					country, awards, poster, movieRatings, metascore, imdbRating, imdbVotes, imdbID);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Error parsing movie information from JsonObject", e);
		}
	}

}