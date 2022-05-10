package application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class QueryController {

	@Autowired
	private QueryService queryService;
	
	@RequestMapping(value="/ask", method=RequestMethod.GET)
    public String queryForm(Model model) {
        model.addAttribute("googleQuery", new GoogleQuery());
        return "queryPage";
    }

    @RequestMapping(value="/ask", method=RequestMethod.POST)
    public String querySubmit(@ModelAttribute GoogleQuery googleQuery, Model model, @AuthenticationPrincipal User asker) {
        model.addAttribute("googleQuery", googleQuery);
        googleQuery.setUser(asker);
        queryService.save(googleQuery);
        model.addAttribute("googleQuery", new GoogleQuery());
        return "redirect:/ask";
    }

}
