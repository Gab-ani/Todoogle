package emailing;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import application.User;
import queries.GoogleQuery;
import security.EmailVerificationToken;

@Component
public class EMailService {

	private EMailFormatter formatter;
    private EMailSender emailSender;
    private QueryBatchMapper emailGrouper;

	public void sendBatch(List<GoogleQuery> queryBatch) {
		var sendToList = emailGrouper.groupByReceiver(queryBatch);
		sendToList.forEach((address, themes) -> {
			//async.run(() -> send(address, formatter.letterFromThemesMap(themes)));
			emailSender.sendPackage(address, formatter.letterFromThemesMap(themes));
		});
	}

	public void sendConfirmationEmail(User user, EmailVerificationToken token) {
		
		emailSender.sendVerificationEmail(user.getEmail(), formatter.confirmationLetter(token));
		
	}
	
    @Autowired
	public void setFormatter(EMailFormatter formatter) {
		this.formatter = formatter;
	}
    
    @Autowired
	public void setEmailSender(EMailSender emailSender) {
		this.emailSender = emailSender;
	}

    @Autowired
	public void setEmailGrouper(QueryBatchMapper emailGrouper) {
		this.emailGrouper = emailGrouper;
	}
}
