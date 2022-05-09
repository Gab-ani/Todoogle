package application;

import org.springframework.data.jpa.repository.JpaRepository;

import security.EmailVerificationToken;


public interface VerificationTokenDAO extends JpaRepository<EmailVerificationToken, Long> {
	
	EmailVerificationToken findByTokenValue(String token);

	EmailVerificationToken findByUser(User user);
	
}
