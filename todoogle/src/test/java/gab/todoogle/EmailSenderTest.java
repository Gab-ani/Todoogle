package gab.todoogle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.io.IOException;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import emailing.EMailSender;

public class EmailSenderTest {

	@Test
	void sendTest() {
		var testedSender = new EMailSender();
		
		JavaMailSender senderMock = Mockito.mock(JavaMailSender.class);
		
		JavaMailSender actualSender = new JavaMailSenderImpl();
//		MimeMessage messageMock = Mockito.mock(MimeMessage.class);
		MimeMessage workingMessage = actualSender.createMimeMessage();
		when(senderMock.createMimeMessage()).thenReturn(workingMessage);
		testedSender.setEmailSender(senderMock);
		
		testedSender.sendPackage("to", "text");
		


		verify(senderMock, times(1)).send(any(MimeMessage.class));
		
		// TODO import email message parser?
		
//		MimeMessage expectedMessage = actualSender.createMimeMessage();
//      MimeMessageHelper helper = new MimeMessageHelper(expectedMessage, "utf-8");
//		try {
//			helper.setText("text", true);
//			helper.setTo("to");
//			helper.setSubject("Todays package");
//			helper.setFrom("noreply.todoogle@gmail.com");
//		} catch (MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		try {
//			assertTrue(expectedMessage.getFrom().toString().contains("noreply.todoogle@gmail.com") && 
//					workingMessage.getFrom().toString().contains("noreply.todoogle@gmail.com"));
////			assertEquals(expectedMessage.getFrom(), workingMessage.getFrom());
//		} catch (MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}
	
}
