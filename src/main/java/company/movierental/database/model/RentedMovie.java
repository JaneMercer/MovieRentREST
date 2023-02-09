package company.movierental.database.model;

import java.time.LocalDate;

import company.movierental.json.gson.DataBaseElement;
import company.movierental.service.InvoiceService;

public class RentedMovie implements DataBaseElement {

	private Movie movie;
	private LocalDate startDate;
	private LocalDate endDate;
	private User user;
	private double rentalPrice;

	// Getters and setters
	public Movie getMovie() {
		return movie;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public User getUser() {
		return user;
	}

	public double getRentalPrice() {
		return rentalPrice;
	}

	// constructor
	public RentedMovie(Movie movie, LocalDate startDate, LocalDate endDate, User user) {
		this.movie = movie;
		this.startDate = startDate;
		this.endDate = endDate;
		this.user = user;
		this.rentalPrice = InvoiceService.calculateRentalPrice(movie, startDate, endDate);
	}
	// toString()

	@Override
	public String toString() {
		return "RentedMovie [movie=" + movie + ", startDate=" + startDate + ", endDate=" + endDate + ", user=" + user
				+ ", rentalPrice=" + rentalPrice + "]";
	}

	@Override
	public String getType() {
		return "rentedMovie";
	}

}
