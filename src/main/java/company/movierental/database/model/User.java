package company.movierental.database.model;

import java.util.ArrayList;

import company.movierental.json.gson.DataBaseElement;

public class User implements DataBaseElement {
	private String name;
	private String email; // used as a unique key
	private String password;
	private ArrayList<RentalOrder> rentalOrders;

	// Getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<RentalOrder> getRentalOrders() {
		return rentalOrders;
	}

	public void setRentalOrders(ArrayList<RentalOrder> rentalOrders) {
		this.rentalOrders = rentalOrders;
	}

	// constructor
	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
		this.rentalOrders = new ArrayList<>();
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", password=" + password + ", rentalOrders=" + rentalOrders
				+ "]";
	}
	
	@Override
	public String getType() {
		return "user";
	}

}
