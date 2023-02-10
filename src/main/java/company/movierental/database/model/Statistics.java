package company.movierental.database.model;


public class Statistics implements DataBaseElement {

	private String someData;

	public Statistics(String someData) {
		this.someData = someData;
	}

	public String getSomeData() {
		return someData;
	}

	public void setSomeData(String someData) {
		this.someData = someData;
	}
	
	@Override
	public String getType() {
		return "stats";
	}
}
