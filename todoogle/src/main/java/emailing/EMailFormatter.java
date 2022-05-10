package emailing;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import security.EmailVerificationToken;

@Component
public class EMailFormatter {

	public String letterFromThemesMap(ArrayList<String> themes) {
		String letter = "";
		for (String theme : themes) {
			letter += "<h4><a href=\"" + googleQuery(theme) + "\">" + theme +  "</a></h4>";
		}
		return letter;
	}
	
	private String googleQuery(String question) {
		return "https://www.google.com/search?q=" + question.replace("\\s", "+");
	}

	public String confirmationLetter(EmailVerificationToken token) {
		String letter = "";
		letter += "<h4><a href=\"" + verifyLink(token.getToken()) + "\">" + "click here to confirm your registration" +  "</a></h4>";
		return letter;
	}
	
	private String verifyLink(String token) {
		// TODO? "unlocalhost" if publish :D
		// TODO? make it a property variable 
		return "https://localhost:8080/register/confirm/?token=" + token;
	}
}
