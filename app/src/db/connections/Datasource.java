package db.connections;

public enum Datasource {
	GRAPH("gds"),
	RELATIONAL("rds");

	private String value;
	
	private Datasource(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
