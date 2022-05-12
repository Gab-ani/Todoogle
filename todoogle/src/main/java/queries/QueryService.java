package queries;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.QueryDAO;

@Service
public class QueryService {
	
	@Autowired
	QueryDAO queryDAO;
	
	public void save(GoogleQuery query) {
		queryDAO.save(query);
	}
	
	public void delete(GoogleQuery query) {
		queryDAO.delete(query);
	}

	public List<GoogleQuery> fetchByMinute(String day, String time) {
		return queryDAO.getNextMinuteQueries(day, time);
	}
	
}
