package emailing;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {

	private JavaMailSender emailSender;
	
	public void send(String to, String text) {
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
	}
	
	@Autowired
	public void setEmailSender(JavaMailSender emailSender) {
		this.emailSender = emailSender;
	}
	
}
