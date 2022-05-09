package emailing;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EMailSender {

	private JavaMailSender emailSender;
	
	public void sendPackage(String to, String text) {
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

	public void sendVerificationEmail(String to, String confirmationLetter) {
		MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		try {
			helper.setText(confirmationLetter, true);
			helper.setTo(to);
			helper.setSubject("Please confirm your email");
			helper.setFrom("noreply.todoogle@gmail.com");
			emailSender.send(mimeMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
}
