package security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import application.UserService;
import exceptions.IncorrectInputsException;
import exceptions.UserAlreadyExistException;
import exceptions.WrongTokenException;

@RestController
@RequestMapping("/register")
public class RegistrationController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
//	@GetMapping
//	public String registerForm() {
//		// TODO "after registration you'll need to confirm your email, check blahblahblah"
//		return "registration";
//	}
	
	@GetMapping("/confirm/*")
	public String confirmRegistration(@RequestParam("token") String token) {
		
		try {
			userService.confirmUser(token);
		} catch (WrongTokenException e) {
			//TODO redirect gtfo
			e.printStackTrace();
			return "confirmationFailure";
		}
		// TODO design ALSO redirect in 10 seconds or whatever
		return "confirmationSuccess";
	}
	
	@PostMapping
	public String processRegistration(RegistrationForm form) {
		
		try {
			userService.saveFromForm(form, passwordEncoder);
		} catch (IncorrectInputsException ie) {
			// TODO show message
			ie.printStackTrace();
		} catch (UserAlreadyExistException ae) {
			// TODO show message
			ae.printStackTrace();
		}
		
		return "redirect:/login";
	}
	
}
