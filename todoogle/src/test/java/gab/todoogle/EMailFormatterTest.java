package gab.todoogle;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class EMailFormatterTest {

	@Test
	void formattingTest() {
		EMailFormatter formatter = new EMailFormatter();
		
		var testThemes = new ArrayList<String>();
		String theme1 = "dsa asda3156;;; ;;qr{}f1.?";
		String theme2 = "lorem ipsum dolor sit amet";
		String theme3 = "!@#@33$   $5%%^&^*(Y* %*& %^(  589631p[}";
		testThemes.add(theme1);
		testThemes.add(theme2);
		testThemes.add(theme3);
		
		String expected = "<h4><a href=\"" + "https://www.google.com/search?q=" + theme1.replace("\\s", "+") + "\">" + theme1 +  "</a></h4>" +
				"<h4><a href=\"" + "https://www.google.com/search?q=" + theme2.replace("\\s", "+") + "\">" + theme2 +  "</a></h4>" +
				"<h4><a href=\"" + "https://www.google.com/search?q=" + theme3.replace("\\s", "+") + "\">" + theme3 +  "</a></h4>";
		
		String actual = formatter.letterFromThemesMap(testThemes);
		assertEquals(expected, actual);
	}
	
}
