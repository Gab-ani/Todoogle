package security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import application.IncorrectInputsException;
import application.UserAlreadyExistException;
import application.UserService;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	
	@Autowired
	private UserService userDAO;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping
	public String registerForm() {
		return "registration";
	}
	
	@PostMapping
	public String processRegistration(RegistrationForm form) {
		
		try {
			userDAO.saveFromForm(form, passwordEncoder);
		} catch (IncorrectInputsException ie) {
			System.out.println("инпут");
			// TODO show message
			ie.printStackTrace();
		} catch (UserAlreadyExistException ae) {
			System.out.println("база");
			// TODO show message
			ae.printStackTrace();
		}
		
		return "redirect:/login";
	}
	
}
