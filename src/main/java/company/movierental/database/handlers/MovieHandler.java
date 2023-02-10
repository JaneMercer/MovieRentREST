package company.movierental.database.handlers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
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
	 * Returns a list of all movies stored in the database.
	 *
	 * @return A list of all movies stored in the database.
	 */
	public List<Movie> getMovies() {
		return this.database.getMovies();
	}

	/**
	 * Method to add a movie to the local JSON file.
	 *
	 * @param movie A movie to be added.
	 * @return Returns `true` if the movie was added successfully and the database
	 *         was saved, `false` otherwise.
	 */
	public boolean addMovie(Movie movie) {
	    if (this.database == null) {
	        return false;
	    }

	    if (!database.getMovies().stream()
	            .anyMatch(existingMovie -> existingMovie.getImdbID().equals(movie.getImdbID()))) {
	        this.database.getMovies().add(movie);
		    this.database.setLastEditDate(LocalDateTime.now());
		    return saveDatabase();
	    } else {
	        return false;
	    }
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

	private static String getValueOrDefault(JsonObject json, String key, String defaultValue) {
		JsonElement element = json.get(key);
		return element != null && !element.isJsonNull() ? element.getAsString() : defaultValue;
	}
	
	public static Movie createMovieFromJson(JsonObject json) {
		DateTimeFormatter formatter = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("yyyy-MM-dd")
				.toFormatter(Locale.ENGLISH);

		JsonArray ratings = json.getAsJsonArray("ratings");
		List<Rating> movieRatings = new ArrayList<>();
		if (ratings != null) {
			for (JsonElement ratingElement : ratings) {
				JsonObject rating = ratingElement.getAsJsonObject();
				movieRatings.add(new Rating(getValueOrDefault(rating, "Source", "N/A"),
						getValueOrDefault(rating, "value", "N/A")));
			}
		}

		String title = getValueOrDefault(json, "title", "N/A");
		String year = getValueOrDefault(json, "year", "N/A");
		String rated = getValueOrDefault(json, "rated", "N/A");
		String releaseDateJson = getValueOrDefault(json, "releaseDate", "N/A");
		LocalDate releaseDate = null;
		if (!releaseDateJson.equals("N/A") ) {
			releaseDate = LocalDate.parse(releaseDateJson, formatter);
		}
		String runtime = getValueOrDefault(json, "runtime", "N/A");
		String genre = getValueOrDefault(json, "genre", "N/A");
		String director = getValueOrDefault(json, "director", "N/A");
		String writer = getValueOrDefault(json, "writer", "N/A");
		String actors = getValueOrDefault(json, "actors", "N/A");
		String plot = getValueOrDefault(json, "plot", "N/A");
		String language = getValueOrDefault(json, "language", "N/A");
		String country = getValueOrDefault(json, "country", "N/A");
		String awards = getValueOrDefault(json, "awards", "N/A");
		String poster = getValueOrDefault(json, "poster", "N/A");
		String metascore = getValueOrDefault(json, "metascore", "N/A");
		String imdbRating = getValueOrDefault(json, "imdbRating", "N/A");
		String imdbVotes = getValueOrDefault(json, "imdbVotes", "N/A");
		String imdbID = getValueOrDefault(json, "imdbID", "N/A");

		if (title.equals("N/A") || imdbID.equals("N/A") || releaseDate == null) {
			throw new IllegalArgumentException(
					"Title, imdbID, and Released are required fields, cannot be null. Movie won't be created.");
		}

		return new Movie(title, year, rated, releaseDate, runtime, genre, director, writer, actors, plot, language,
				country, awards, poster, movieRatings, metascore, imdbRating, imdbVotes, imdbID);

	}

}