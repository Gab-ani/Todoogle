package emailing;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import application.GoogleQuery;
import application.QueryBatchMapper;

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
