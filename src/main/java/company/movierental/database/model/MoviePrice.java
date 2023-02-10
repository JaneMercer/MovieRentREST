package company.movierental.database.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class MoviePrice {
	private double pricePerWeek;
	private LocalDate releaseDate;

	private static double NewMoviePrice = 5;
	private static double RegularMoviePrice = 3.49;
	private static double OldMoviePrice = 1.99;

	public MoviePrice(LocalDate releaseDate, LocalDate date) {
		this.releaseDate = releaseDate;
		this.pricePerWeek = calculatePricePerWeek(date);
	}

	public MoviePrice(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
		this.pricePerWeek = calculatePricePerWeek(LocalDate.now());
	}

	// Also possible to create 3 classes of New\Old\Regular Movie but I thought that
	// it would be too much for this purpose
	private double calculatePricePerWeek(LocalDate currentDate) {
		long weeksSinceRelease = ChronoUnit.WEEKS.between(releaseDate, currentDate);
		if (weeksSinceRelease <= 52) {
			return NewMoviePrice;
		} else if (weeksSinceRelease > 52 && weeksSinceRelease <= 156) {
			return RegularMoviePrice;
		} else {
			return OldMoviePrice;
		}
	}

	public double getPricePerWeek() {
		return pricePerWeek;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public static double countRentedMoviePrice(Movie movie, LocalDate startDate, LocalDate endDate) {
		int totalWeeks = (int) ChronoUnit.WEEKS.between(startDate, endDate);
		double price = 0.0;
		for (int i = 0; i < totalWeeks; i++) {
			LocalDate currentCalculatedWeekDate = startDate.plusWeeks(i);
			double pricePerCurrentWeek = new MoviePrice(movie.getReleaseDate(), currentCalculatedWeekDate)
					.getPricePerWeek();
			price += pricePerCurrentWeek;
		}
		return price;
	}
}