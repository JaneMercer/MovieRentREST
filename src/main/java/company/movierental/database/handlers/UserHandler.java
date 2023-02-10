package company.movierental.database.handlers;

import java.time.LocalDateTime;
import java.util.List;

import company.movierental.database.model.User;

public class UserHandler extends JsonHandler {

	/**
	 * Constructor that sets the file path to the local JSON file.
	 * 
	 * @param filePath The file path to the local JSON file.
	 */
	public UserHandler(String filePath) {
		super(filePath);
	}

	/**
	 * Returns a list of all users stored in the database.
	 *
	 * @return A list of all users stored in the database.
	 */
	public List<User> getUsers() {
		return this.database.getUsers();
	}

	/**
	 * Adds a new user to the database.
	 * 
	 * @param user The user to be added.
	 * @return True if the user was successfully added, false otherwise.
	 */
	public boolean addUser(User user) {

		// Check if the user already exists in the database
		User existingUser = getUserByEmail(user.getEmail());
		if (existingUser != null) {
			return false;
		}

		this.database.getUsers().add(user);
		this.database.setLastEditDate(LocalDateTime.now());
		return saveDatabase();
	}

	/**
	 * 
	 * Retrieve a user by their email.
	 * 
	 * @param email of the user to retrieve
	 * 
	 * @return a {@link User} object if a user with the specified email exists, or
	 *         {@code null} otherwise
	 */
	public User getUserByEmail(String email) {
		if (this.database == null) {
			return null;
		}
		List<User> users = this.database.getUsers();
		for (User user : users) {
			if (user.getEmail().equals(email)) {
				return user;
			}
		}
		return null;
	}

}
