package application;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

import emailing.EMailService;
import exceptions.IncorrectInputsException;
import exceptions.UserAlreadyExistException;
import exceptions.WrongTokenException;
import security.EmailVerificationToken;
import security.RegistrationForm;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	UserDAO userDAO;
	
	@Lazy
	@Autowired
	EMailService emailService;
	
	@Autowired
	VerificationTokenDAO tokensDAO;
	
	public boolean saveFromForm(RegistrationForm form, PasswordEncoder encoder) throws UserAlreadyExistException, IncorrectInputsException {
		if(formIsValid(form)) {   // --> if not, method exits here with an exception that must be caught in the RegistrationController / any calling code
			User newUser = form.toUser(encoder);
			save(newUser);
			initiateConfirmationProcess(newUser);
			return true;
		}
		return false;
	}
	
	public void confirmUser(String tokenValue) throws WrongTokenException {
		var token = tokensDAO.findByTokenValue(tokenValue);
		if(token != null) {
			// TODO expiration
			User user = token.getUser();
			user.enable();
			userDAO.save(user);
			return;
		}
		throw new WrongTokenException();
	}
	
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDAO.findByUsername(username);
		if (user != null) {
		  return user;
		}
		throw new UsernameNotFoundException("User '" + username + "' not found");
	}
	
	public void save(User u) {
		userDAO.save(u);
	}

	public User getAdmin() {
		return userDAO.getAdmin();
	}
	
	private boolean formIsValid(RegistrationForm form) throws UserAlreadyExistException, IncorrectInputsException {
		
		if(usernameAlreadyRegistered(form.getUsername())) {
			throw new UserAlreadyExistException("Username " + form.getUsername() + " already taken");
		}
		if(emailAlreadyRegistered(form.getEmail())) {
			throw new UserAlreadyExistException("Account for email " + form.getEmail() + " already registered");
		}
		if(form.getEmail().isBlank() || form.getPassword().isBlank() || form.getUsername().isBlank()) {
			throw new IncorrectInputsException("Please fill in all fields");
		}
		
		return true;
	}
		
	private void initiateConfirmationProcess(User user) {
		var newToken = new EmailVerificationToken(user, UUID.randomUUID().toString());
		tokensDAO.save(newToken);
		
		emailService.sendConfirmationEmail(user, newToken);
	}
	
	private boolean usernameAlreadyRegistered(String username) {
		User user = userDAO.findByUsername(username);
	    if (user != null) {
	    	return true;
	    }
	    return false;
	}
	
	private boolean emailAlreadyRegistered(String address) {
		User user = userDAO.findByEmail(address);
	    if (user != null) {
	    	return true;
	    }
	    return false;
	}
	

}
