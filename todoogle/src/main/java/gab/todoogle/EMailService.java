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

	private EMailFormatter formatter;
    private EmailSender emailSender;
    private QueryBatchMapper emailGrouper;

	public void sendBatch(List<GoogleQuery> queryBatch) {
		var sendToList = emailGrouper.groupByReceiver(queryBatch);
		sendToList.forEach((address, themes) -> {
			//async.run(() -> send(address, formatter.letterFromThemesMap(themes)));
			emailSender.send(address, formatter.letterFromThemesMap(themes));
		});
	}
	
    @Autowired
	public void setFormatter(EMailFormatter formatter) {
		this.formatter = formatter;
	}
    
    @Autowired
	public void setEmailSender(EmailSender emailSender) {
		this.emailSender = emailSender;
	}

    @Autowired
	public void setEmailGrouper(QueryBatchMapper emailGrouper) {
		this.emailGrouper = emailGrouper;
	}
}
