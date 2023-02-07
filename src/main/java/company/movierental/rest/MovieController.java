package company.movierental.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import company.movierental.database.model.Movie;
import company.movierental.omdb.MovieFetcher;

@RestController
@RequestMapping("/movies")
public class MovieController {
	private List<Movie> movies = new ArrayList<>();

	@ResponseBody
	@GetMapping("/movies")
	public List<Movie> getMovies() {
		//change this line to read from database
		this.movies = MovieFetcher.getMoviesBySearch("The god", 1, 2022, "794669bb");
		return movies;
	}
	
	@PostMapping
    public void addMovies(@RequestBody List<Movie> movies) {
        // Write code to read the existing local database JSON file
        // Update the "movies" field with the new movies from the request body
        // Write code to save the updated local database JSON file
    }

}
