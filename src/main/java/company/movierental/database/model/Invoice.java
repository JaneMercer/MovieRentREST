package company.movierental.database.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class Invoice implements DataBaseElement {
	private UUID orderID;
	private User client;
	private ArrayList<RentedMovie> moviesForRent;
	private Boolean isPaid;
	private LocalDateTime creationDateTime;
	private LocalDateTime paidDateTime;
	private String paymentInfo;
	private Double totalPrice;

	public UUID getOrderID() {
		return orderID;
	}

	public void setOrderID(UUID orderID) {
		this.orderID = orderID;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public ArrayList<RentedMovie> getMoviesForRent() {
		return moviesForRent;
	}

	public void setMoviesForRent(ArrayList<RentedMovie> movies) {
		this.moviesForRent = movies;
	}

	public LocalDateTime getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(LocalDateTime creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public Boolean getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(Boolean isPaid) {
		this.isPaid = isPaid;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Invoice() {
	}

	@Override
	public String toString() {
		return "RentalOrder [client=" + client + ", movies=" + moviesForRent + ", isPaid=" + isPaid + ", totalPrice="
				+ totalPrice + "]";
	}

	public LocalDateTime getInvoiceDate() {
		return creationDateTime;
	}

	public LocalDateTime getPaidDateTime() {
		return paidDateTime;
	}

	public void setPaidDateTime(LocalDateTime paidDateTime) {
		this.paidDateTime = paidDateTime;
	}

	public String getPaymentInfo() {
		return paymentInfo;
	}

	public void setPaymentInfo(String paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	@Override
	public String getType() {
		return "invoice";
	}

}
