package company.movierental.database.model;

import java.util.ArrayList;
import java.util.UUID;

import company.movierental.json.gson.DataBaseElement;
import company.movierental.utils.UniqueIDGenerator;

public class RentalOrder implements DataBaseElement  {
	private static final Boolean False = null;
	private UUID orderID;
	private User client;
	private ArrayList<RentedMovie> movies;
	private Boolean isPaid;
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

	public ArrayList<RentedMovie> getMovies() {
		return movies;
	}

	public void setMovies(ArrayList<RentedMovie> movies) {
		this.movies = movies;
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

	
	public RentalOrder(String orderID, User client, Boolean isPaid) {
		super();
		this.orderID = UniqueIDGenerator.generateUniqueID();
		this.client = client;
		this.isPaid = False;
	}

	@Override
	public String toString() {
		return "RentalOrder [client=" + client + ", movies=" + movies + ", isPaid=" + isPaid + ", totalPrice="
				+ totalPrice + "]";
	}
	
	@Override
	public String getType() {
		return "order";
	}

}
