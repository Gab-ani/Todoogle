package gab.todoogle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserDAO extends JpaRepository<User, Long> {
	 User findByUsername(String username);
	 
	 @Query(value = "SELECT * FROM users WHERE username = 'admin'",
			 nativeQuery = true)
	 User getAdmin();
}


