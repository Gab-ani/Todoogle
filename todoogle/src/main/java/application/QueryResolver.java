package application;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import emailing.EMailService;

@Component
public class QueryResolver{

	@Autowired
	private QueryService queryService;
	
	@Autowired
	EMailService emailService;
	
	private List<GoogleQuery> currentQueryBatch;
	
	public void loadNextQueryBatch() {
		
		LocalDate today = LocalDate.now();
		String day = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault()));
		LocalTime nextMinute = LocalTime.now().plusMinutes(1);
		String time = nextMinute.format(DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault()));
		
		System.out.println("запрашиваю на " + day + "   " + time);
		currentQueryBatch = queryService.fetchByMinute(day, time);
		
	}
	
	public void sendCurrentQueryBatch() {
		if(currentQueryBatch != null) {
			emailService.sendBatch(currentQueryBatch);
		} else {
			System.out.println("нечего слать");
		}
	}
	
	@Scheduled(cron = "0 * * * * ?")
	public void resolveBatch() throws InterruptedException {		
		sendCurrentQueryBatch();
		loadNextQueryBatch();
	}
	
}
