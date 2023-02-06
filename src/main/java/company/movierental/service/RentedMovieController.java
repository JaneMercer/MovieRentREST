package company.movierental.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import company.movierental.database.model.Movie;

public class RentedMovieController {
	
	public static double calculateRentalPrice(Movie movie, LocalDate startDate, LocalDate endDate) {
	    int totalWeeks = (int) ChronoUnit.WEEKS.between(startDate, endDate);
	    double price = 0.0;
	    for (int i = 0; i < totalWeeks; i++) {
	        LocalDate currentCalculatedWeekDate = startDate.plusWeeks(i);
	        double pricePerCurrentWeek = new MoviePrice(movie,currentCalculatedWeekDate).getPricePerWeek();
	        price += pricePerCurrentWeek;
	    }
	    return price;
	}
}
