package company.movierental.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import company.movierental.database.model.Movie;

public class MoviePrice {
	private double pricePerWeek;
	private LocalDate releaseDate;

	public MoviePrice(LocalDate releaseDate, LocalDate date) {
		this.releaseDate = releaseDate;
		this.pricePerWeek = calculatePricePerWeek(date);
	}

	public MoviePrice(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
		this.pricePerWeek = calculatePricePerWeek(LocalDate.now());
    }

	private double calculatePricePerWeek(LocalDate currentDate) {
        long weeksSinceRelease = ChronoUnit.WEEKS.between(releaseDate, currentDate);
        if (weeksSinceRelease <= 52) {
            return 5;
        } else if (weeksSinceRelease > 52 && weeksSinceRelease <= 156) {
            return 3.49;
        } else {
            return 1.99;
        }
    }


	public double getPricePerWeek() {
		return pricePerWeek;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}
}