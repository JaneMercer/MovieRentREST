package company.movierental.database.model;

import company.movierental.json.gson.DataBaseElement;

public class Rating implements DataBaseElement {
	private String source;
	private String value;

	// Getters and setters
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	// constructor
	public Rating(String source, String value) {
		this.source = source;
		this.value = value;
	}

	// toString()
	@Override
	public String toString() {
		return "Rating [source=" + source + ", value=" + value + "]";
	}

	@Override
	public String getType() {
		return "rating";
	}

}
