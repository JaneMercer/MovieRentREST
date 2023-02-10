package company.movierental.database.handlers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.UUID;

import company.movierental.database.model.Invoice;
import company.movierental.database.model.Movie;
import company.movierental.database.model.MoviePrice;
import company.movierental.database.model.RentedMovie;
import company.movierental.database.model.User;

public class InvoiceHandler extends JsonHandler {
	//TODO: create InvoiceHandler methods
	
	/**
	 * Constructor that sets the file path to the local JSON file.
	 * 
	 * @param filePath The file path to the local JSON file.
	 */
	public InvoiceHandler(String filePath) {
		super(filePath);
	}
	
	

//	public Invoice calculateInvoice(User client, ArrayList<RentedMovie> moviesForRent) {
//		Invoice invoice = new Invoice();
//		invoice.setOrderID(UUID.randomUUID());
//		invoice.setClient(client);
//		invoice.setMoviesForRent(moviesForRent);
//		invoice.setIsPaid(false);
//		invoice.setCreationDateTime(LocalDateTime.now());
//		invoice.setPaidDateTime(null);
//		invoice.setPaymentInfo(null);
//
//		double totalPrice = 0.0;
//		for (RentedMovie rentedMovie : moviesForRent) {
//			double rentalPrice = InvoiceHandler.countRentedMoviePrice(rentedMovie.getMovie(),
//					rentedMovie.getStartDate(), rentedMovie.getEndDate());
//			rentedMovie.setRentalPrice(rentalPrice);
//			totalPrice += rentalPrice;
//		}
//
//		invoice.setTotalPrice(totalPrice);
//		return invoice;
//	}

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
