package company.movierental.database.model;

import java.util.ArrayList;
import java.util.List;


public class Category implements DataBaseElement {
	private String name;
	private ArrayList<Movie> movies;

	// Getters and setters
	public String getName() {
		return name;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addMovie(Movie movie) {
		this.movies.add(movie);
	}

	public void removeMovie(Movie movie) {
		this.movies.remove(movie);
	}

	// constructor
	public Category(String name) {
		this.name = name;
		this.movies = new ArrayList<>();
	}

	// toString()
	@Override
	public String toString() {
		return "Category [name=" + name + ", movies=" + movies + "]";
	}

	@Override
	public String getType() {
		return "category";
	}
}