package gab.todoogle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

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
	
}
