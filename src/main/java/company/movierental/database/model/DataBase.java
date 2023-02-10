package company.movierental.database.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The `Database` class represents a database object. It stores information
 * about movies, users, invoices, categories, managers and statistics.
 * 
 * @author Iryna
 * @version 0.1
 * @since 10.02.23
 */
public class Database implements DataBaseElement {
	/**
	 * The name of the database.
	 */
	private String name;

	/**
	 * The file path of the database.
	 */
	private String path;

	/**
	 * The date and time of the database creation.
	 */
	private LocalDateTime creationDate;

	/**
	 * The date and time of the last modification to the database.
	 */
	private LocalDateTime lastEditDate;

	/**
	 * The format of the database (JSON or YAML).
	 */
	private String format;

	/**
	 * The list of managers who have access to the database.
	 */
	private ArrayList<Manager> managers;

	/**
	 * The list of users who have made orders.
	 */
	private ArrayList<User> users;

	/**
	 * The list of invoices generated from the orders.
	 */
	private ArrayList<Invoice> orders;

	/**
	 * The list of movie categories.
	 */
	private ArrayList<Category> categories;

	/**
	 * The list of movies stored in the database.
	 */
	private ArrayList<Movie> movies;

	/**
	 * The statistics generated from the data stored in the database.
	 */
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

	public Database(String name, String path, ArrayList<Manager> managers) {
		super();
		this.name = name;
		this.path = path;
		this.managers = managers;
		this.users = new ArrayList<>();
		this.orders = new ArrayList<>();
		this.categories = new ArrayList<>();
		this.movies = new ArrayList<>();
		Statistics tempStats = new Statistics();
		tempStats.addStringData("Database created");
		this.stats = tempStats;
	}

	@Override
	public String toString() {
		return "DataBase [name=" + name + ", path=" + path + ", creationDate=" + creationDate + ", lastEditDate="
				+ lastEditDate + ", managers=" + managers + ", format=" + format + "]";
	}

	@Override
	public String getType() {
		return "database";
	}

}
