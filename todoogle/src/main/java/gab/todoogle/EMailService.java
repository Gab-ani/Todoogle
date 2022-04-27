package gab.todoogle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EMailService {

	@Autowired
	EMailFormatter formatter;
	
    @Autowired
    private JavaMailSender emailSender;

	public void sendBatch(List<GoogleQuery> queryBatch) {
		var sendToList = sortByReceiver(queryBatch);
		sendToList.forEach((address, themes) -> {
			//async.run(() -> send(address, formatter.letterFromThemesMap(themes)));
			send(address, formatter.letterFromThemesMap(themes));
		});
	}
	
	private HashMap< String, ArrayList<String> > sortByReceiver(List<GoogleQuery> queryBatch) {		// what we achieve is a number of themes mapped to an email address like
		var emailReceiversMap = new HashMap< String, ArrayList<String> >();							// ( "abc@gmail.com" : { "why don't crocodiles fly" , "how to cook mac&cheese" , "news 26.04" } )
																									// ( "def@gmail.com" : { "..." , "..." } )
		queryBatch.forEach(query -> {																// etc.
			if(!emailReceiversMap.containsKey(query.getUser().getEmail())) {
				emailReceiversMap.put(query.getUser().getEmail(), new ArrayList<>());
			}
			emailReceiversMap.get(query.getUser().getEmail()).add(query.getQuery());
		});
		
		return emailReceiversMap;
	}
	
	public void send(String to, String text) {
//		SimpleMailMessage message = new SimpleMailMessage(); 
//        message.setFrom("noreply.todoogle@gmail.com");
//        message.setTo(to); 
//        message.setSubject("Todays package"); 
//        message.setText(text);
//        emailSender.send(message);
        
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		try {
			helper.setText(text, true);
			helper.setTo(to);
			helper.setSubject("Todays package");
			helper.setFrom("noreply.todoogle@gmail.com");
			emailSender.send(mimeMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
        
        
		System.out.println("письмо отправлено");
	}
}
