package queries;

public class GoogleQueryResource {

	private String query;
	private String date;
	private String time;
	
	public void setQuery(String query) {
		this.query = query;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public GoogleQuery toGoogleQuery() {
		return new GoogleQuery(this.query, this.date, this.time);
	}
	
}
