package company.movierental.rest;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import company.movierental.database.handlers.MovieHandler;
import company.movierental.database.model.Movie;
import company.movierental.database.model.MoviePrice;
import company.movierental.utils.genkey.ManagerSecurityKey;
import company.movierental.utils.gson.LocalDateTimeTypeAdapter;
import company.movierental.utils.gson.LocalDateTypeAdapter;
import company.movierental.utils.gson.ManagerSecurityKeyTypeAdapter;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
	private final MovieHandler movieHandler;

	public MovieController(MovieHandler movieHandler) {
		this.movieHandler = movieHandler;
	}

	@ResponseBody
	@GetMapping
	public ResponseEntity<List<Movie>> getMovies() {
		List<Movie> movies = movieHandler.getMovies();
		return ResponseEntity.status(HttpStatus.OK).body(movies);
	}

	@GetMapping("/price/{imdbID}")
	public ResponseEntity<Double> getCurrentMoviePrice(@PathVariable String imdbID) {
		Movie movie = movieHandler.getMovieByImdbID(imdbID);
		if (movie == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		MoviePrice moviePrice = new MoviePrice(movie.getReleaseDate());
		return ResponseEntity.ok(moviePrice.getPricePerWeek());
	}

	@PostMapping
	public ResponseEntity<Object> addMovie(@RequestBody String jsonString) {
		Gson gson = new GsonBuilder().setPrettyPrinting()
				.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
				.registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
				.registerTypeAdapter(ManagerSecurityKey.class, new ManagerSecurityKeyTypeAdapter()).create();

	JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);

	Movie movie = MovieHandler.createMovieFromJson(jsonObject);
	  if (movie != null && movieHandler.addMovie(movie)) {
	    return ResponseEntity.status(HttpStatus.CREATED).build();
	  } else {
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	  }
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
