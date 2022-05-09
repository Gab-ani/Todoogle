package security;

import org.springframework.data.jpa.repository.JpaRepository;

import application.User;

public interface VerificationTokenDAO extends JpaRepository<EmailVerificationToken, Long> {
	
	EmailVerificationToken findByTokenValue(String token);

	EmailVerificationToken findByUser(User user);
	
}
