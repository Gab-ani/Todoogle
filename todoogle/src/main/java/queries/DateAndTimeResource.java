package queries;

public class DateAndTimeResource {

	private String date;
	private String time;
	
	public DateAndTimeResource(String date, String time) {
		this.date = date;
		this.time = time;
	}
	
	public String getDate() {
		return date;
	}
	
	public String getTime() {
		return time;
	}
	
}
