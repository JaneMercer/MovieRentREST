package company.movierental.database.model;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import company.movierental.json.gson.DataBaseElement;
import company.movierental.utils.UniqueIDGenerator;

public class Movie implements DataBaseElement {
	private UUID movieID;
	private String title;
	private int year;
	private String rated;
	private LocalDate releaseDate;
	private String runtime;
	private String genre;
	private String director;
	private String writer;
	private String actors;
	private String plot;
	private String language;
	private String country;
	private String awards;
	private String poster;
	private List<Rating> ratings;
	private String metascore;
	private String imdbRating;
	private String imdbVotes;
	private String imdbID;

	// Getters and setters

	public UUID getMovieID() {
		return movieID;
	}

	public void setMovieID(UUID movieID) {
		this.movieID = movieID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getRated() {
		return rated;
	}

	public void setRated(String rated) {
		this.rated = rated;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAwards() {
		return awards;
	}

	public void setAwards(String awards) {
		this.awards = awards;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public String getMetascore() {
		return metascore;
	}

	public void setMetascore(String metascore) {
		this.metascore = metascore;
	}

	public String getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(String imdbRating) {
		this.imdbRating = imdbRating;
	}

	public String getImdbVotes() {
		return imdbVotes;
	}

	public void setImdbVotes(String imdbVotes) {
		this.imdbVotes = imdbVotes;
	}

	public String getImdbID() {
		return imdbID;
	}

	public void setImdbID(String imdbID) {
		this.imdbID = imdbID;
	}

	// constructor

	public Movie(String title, LocalDate releaseDate, String imdbID) {
		super();
		this.movieID = UniqueIDGenerator.generateUniqueID();
		this.title = title;
		this.releaseDate = releaseDate;
		this.imdbID = imdbID;
	}

	// constructor form OMDB
	public Movie(String title, int year, String rated, LocalDate releaseDate, String runtime, String genre,
			String director, String writer, String actors, String plot, String language, String country, String awards,
			String poster, List<Rating> ratings, String metascore, String imdbRating, String imdbVotes, String imdbID) {
		super();
		this.movieID = UniqueIDGenerator.generateUniqueID();
		this.title = title;
		this.year = year;
		this.rated = rated;
		this.releaseDate = releaseDate;
		this.runtime = runtime;
		this.genre = genre;
		this.director = director;
		this.writer = writer;
		this.actors = actors;
		this.plot = plot;
		this.language = language;
		this.country = country;
		this.awards = awards;
		this.poster = poster;
		this.ratings = ratings;
		this.metascore = metascore;
		this.imdbRating = imdbRating;
		this.imdbVotes = imdbVotes;
		this.imdbID = imdbID;
	}

	// toString()
	@Override
	public String toString() {
		return "Movie [title=" + title + ", year=" + year + ", rated=" + rated + ", released=" + releaseDate
				+ ", runtime=" + runtime + ", genre=" + genre + ", director=" + director + ", writer=" + writer
				+ ", actors=" + actors + ", plot=" + plot + ", language=" + language + ", country=" + country
				+ ", awards=" + awards + ", poster=" + poster + ", ratings=" + ratings + ", metascore=" + metascore
				+ ", imdbRating=" + imdbRating + ", imdbVotes=" + imdbVotes + ", imdbID=" + imdbID + "]";
	}
	
	@Override
	public String getType() {
		return "movie";
	}

}
