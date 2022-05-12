package gab.todoogle;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;

import javax.mail.internet.MimeMessage;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mail.javamail.JavaMailSender;

import application.User;
import emailing.EMailFormatter;
import emailing.EMailService;
import emailing.QueryBatchMapper;
import queries.GoogleQuery;
import emailing.EMailSender;

public class EMailServiceTest {
	
	@Test
	void sendBatchTest() {
		
		var testedService = new EMailService();
		
		EMailFormatter formatterMock = Mockito.mock(EMailFormatter.class);
		when(formatterMock.letterFromThemesMap(any())).thenReturn("formatted");
		testedService.setFormatter(formatterMock);
		
		EMailSender senderMock = Mockito.mock(EMailSender.class);
		testedService.setEmailSender(senderMock);
		
		QueryBatchMapper mapper  = new QueryBatchMapper();
		testedService.setEmailGrouper(mapper);
		
		testedService.sendBatch(createTestBatch());
		
		verify(formatterMock, times(2)).letterFromThemesMap(any(ArrayList.class));
		verify(senderMock, times(2)).sendPackage(anyString(), anyString());
		
	}
	
	ArrayList<GoogleQuery> createTestBatch() {						// 2 receivers, first has 3 themes, second has 1
		var testBatch = new ArrayList<GoogleQuery>();
		
		var query1 = new GoogleQuery();
		query1.setUser(new User("username1", "password", "email1"));
		query1.setQuery("query1Content");
		
		var query2 = new GoogleQuery();
		query2.setUser(new User("username1", "password", "email1"));
		query2.setQuery("query2Content");
		
		var query3 = new GoogleQuery();
		query3.setUser(new User("username1", "password", "email1"));
		query3.setQuery("query3Content");
		
		var query4 = new GoogleQuery();
		query4.setUser(new User("username2", "password", "email2"));
		query4.setQuery("query4Content");
		
		
		
		testBatch.add(query1);
		testBatch.add(query2);
		testBatch.add(query3);
		testBatch.add(query4);
		return testBatch;
	}
	
	
}
