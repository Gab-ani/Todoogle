package queries;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import application.User;
import application.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AskController {

	@Autowired
	private QueryService queryService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/ask/defaultDateAndTime", method = RequestMethod.GET)
	public DateAndTimeResource getDefaultTime(@AuthenticationPrincipal User asker) {
		return new DateAndTimeResource(asker.getDefaultDate(), asker.getDefaultTime());
	}
	
	@RequestMapping(value = "/ask/defaultDateAndTime", method = RequestMethod.POST)
	public String setDefaultTime(@RequestBody DateAndTimeResource dateTime, @AuthenticationPrincipal User asker) {
		
		asker.setDefaultDateAndTime(dateTime);
		userService.save(asker);
		
		return "changed";
	}
	
    @RequestMapping(value = "/ask", method = RequestMethod.POST)
    public String querySubmit(@RequestBody GoogleQueryResource queryForm, @AuthenticationPrincipal User asker) {
    	var query = queryForm.toGoogleQuery();
    	query.setUser(asker);
        queryService.save(query);
        return "accepted for " + asker.getUsername();
    }

}
