package company.movierental.database.model;

import java.time.LocalDate;

import company.movierental.json.gson.DataBaseElement;
import company.movierental.utils.ManagerSecurityKey;

public class Manager implements DataBaseElement {
	private String fullName;
	private String login;
	private String position;
	private ManagerSecurityKey uniqueKey;
	private LocalDate lastOnline;
	// private List<Edit> lastEdits;

	// Getters and setters
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public ManagerSecurityKey getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(ManagerSecurityKey uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public LocalDate getLastOnline() {
		return lastOnline;
	}

	public void setLastOnline(LocalDate lastOnline) {
		this.lastOnline = lastOnline;
	}

	// Constructor

	@Override
	public String toString() {
		return "Manager [fullName=" + fullName + ", login=" + login + ", position=" + position + ", uniqueKey="
				+ uniqueKey + ", lastOnline=" + lastOnline + "]";
	}

	public Manager(String login, String position, ManagerSecurityKey uniqueKey) {
		super();
		this.login = login;
		this.position = position;
		this.uniqueKey = uniqueKey;
	}

	@Override
	public String getType() {
		return "manager";
	}

}
