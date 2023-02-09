package company.movierental.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import company.movierental.Config;
import company.movierental.database.model.Database;
import company.movierental.database.model.Movie;
import company.movierental.json.JsonHandler;
import company.movierental.json.MovieHandler;
import company.movierental.service.MoviePrice;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
	private final MovieHandler movieHandler;

	public MovieController(MovieHandler movieHandler) {
		this.movieHandler = movieHandler;
	}

	@ResponseBody
	@GetMapping
	public static List<Movie> listMovies() {
		JsonHandler handler = new JsonHandler(Config.FILE_PATH);
		Database database = handler.getDatabase();
		List<Movie> movies = database.getMovies();
		return movies;
	}
	
	@GetMapping("/price/{imdbID}")
	public ResponseEntity<Double> getPricePerWeek(@PathVariable String imdbID) {
	Movie movie = movieHandler.getMovieByImdbID(imdbID);
	if (movie == null) {
	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	MoviePrice moviePrice = new MoviePrice(movie.getReleaseDate());
	return ResponseEntity.ok(moviePrice.getPricePerWeek());
	}

	@PostMapping
	public ResponseEntity<Object> addMovies(@RequestBody List<Movie> movies) {
		if (movieHandler.addMovies(movies)) {
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@PutMapping
	public ResponseEntity<Object> updateMovie(@RequestBody Movie updatedMovie) {
		if (movieHandler.updateMovie(updatedMovie)) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@DeleteMapping("/{imdbID}")
	public ResponseEntity<Object> deleteMovie(@PathVariable String imdbID) {
		if (movieHandler.removeMovie(imdbID)) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	

}
