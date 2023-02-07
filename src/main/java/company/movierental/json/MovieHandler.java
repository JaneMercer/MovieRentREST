package company.movierental.json;

import java.time.LocalDateTime;
import java.util.List;

import company.movierental.database.model.Movie;

public class MovieHandler extends JsonHandler {
	public MovieHandler(String filePath) {
		super(filePath);
	}

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
}