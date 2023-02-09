package company.movierental.database.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import company.movierental.json.gson.DataBaseElement;

public class Database implements DataBaseElement {
	private String name;
	private String path;
	private LocalDateTime creationDate;
	private LocalDateTime lastEditDate;
	private String format;
	private ArrayList<Manager> managers;
	private ArrayList<User> users;
	private ArrayList<Invoice> orders;
	private ArrayList<Category> categories;
	private ArrayList<Movie> movies;
	private Statistics stats;

	// Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public LocalDateTime getLastEditDate() {
		return lastEditDate;
	}

	public void setLastEditDate(LocalDateTime lastEditDate) {
		this.lastEditDate = lastEditDate;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public ArrayList<Manager> getManagers() {
		return managers;
	}

	public void setManagers(ArrayList<Manager> managers) {
		this.managers = managers;
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	public ArrayList<Invoice> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Invoice> orders) {
		this.orders = orders;
	}

	public ArrayList<Category> getCategories() {
		return categories;
	}

	public void setCategories(ArrayList<Category> categories) {
		this.categories = categories;
	}

	public ArrayList<Movie> getMovies() {
		return movies;
	}

	public void setMovies(ArrayList<Movie> movies) {
		this.movies = movies;
	}

	public Statistics getStats() {
		return stats;
	}

	public void setStats(Statistics stats) {
		this.stats = stats;
	}

	// Constructor

	@Override
	public String toString() {
		return "DataBase [name=" + name + ", path=" + path + ", creationDate=" + creationDate + ", lastEditDate="
				+ lastEditDate + ", managers=" + managers + ", format=" + format + "]";
	}

	public Database(String name, String path, ArrayList<Manager> managers) {
		super();
		this.name = name;
		this.path = path;
		this.managers = managers;
		this.users = new ArrayList<>();
		this.orders = new ArrayList<>();
		this.categories = new ArrayList<>();
		this.movies = new ArrayList<>();
		this.stats = new Statistics("Database created");
	}

	@Override
	public String getType() {
		return "database";
	}

}
