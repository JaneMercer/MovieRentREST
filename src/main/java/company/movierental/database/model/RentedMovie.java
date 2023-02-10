package company.movierental.database.model;

import java.time.LocalDate;

import company.movierental.database.handlers.InvoiceHandler;

public class RentedMovie implements DataBaseElement {
	private Movie movie;
	private LocalDate startDate;
	private LocalDate endDate;
	private double rentalPrice;

	public double getRentalPrice() {
		return InvoiceHandler.countRentedMoviePrice(movie, startDate, endDate);
	}

	public void setRentalPrice(LocalDate startDate, LocalDate endDate) {
		this.rentalPrice = InvoiceHandler.countRentedMoviePrice(movie, startDate, endDate);
	}

	// may be useful in some cases
	public void setRentalPrice(double rentalPrice) {
		this.rentalPrice = rentalPrice;
	}

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

	// constructor
	public RentedMovie(Movie movie, LocalDate startDate, LocalDate endDate) {
		this.movie = movie;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	// toString()

	@Override
	public String toString() {
		return "RentedMovie [movie=" + movie + ", startDate=" + startDate + ", endDate=" + endDate + ", rentalPrice="
				+ rentalPrice + "]";
	}

	@Override
	public String getType() {
		return "rentedMovie";
	}

}
