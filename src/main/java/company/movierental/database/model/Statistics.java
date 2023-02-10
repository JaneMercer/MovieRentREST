package company.movierental.database.model;

import java.util.ArrayList;

/**
 * The `Statistics` class implements the {@link DataBaseElement} interface and
 * is used to store statistical information about the data stored in a database.
 * 
 * @author Iryna
 * @version 0.1
 * @since 06.02.23
 */

public class Statistics implements DataBaseElement {

	private ArrayList<String> someData;

	public ArrayList<String> getSomeData() {
		return someData;
	}

	public void setSomeData(ArrayList<String> someData) {
		this.someData = someData;
	}

	public Statistics() {
		super();
		this.someData = new ArrayList<String>();
	}

	public ArrayList<String> addStringData(String stringData) {
		this.someData.add(stringData);
		return this.someData;
	}

	@Override
	public String getType() {
		return "stats";
	}
}
