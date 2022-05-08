package application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

import security.RegistrationForm;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	UserDAO userDAO;
	
	public boolean saveFromForm(RegistrationForm form, PasswordEncoder encoder) throws UserAlreadyExistException, IncorrectInputsException {
		
		if(formIsValid(form)) {
			save(form.toUser(encoder));
			return true;
		}
		return false;
		
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
	
	public void save(User u) {
		userDAO.save(u);
	}
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDAO.findByUsername(username);
		if (user != null) {
		  return user;
		}
		throw new UsernameNotFoundException("User '" + username + "' not found");
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
	
	public User getAdmin() {
		return userDAO.getAdmin();
	}

}
