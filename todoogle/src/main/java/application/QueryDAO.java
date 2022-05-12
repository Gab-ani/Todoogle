package application;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import queries.GoogleQuery;

public interface QueryDAO extends JpaRepository<GoogleQuery, Long>{
	
	@Query(
		value = "SELECT * FROM future_queries WHERE date = :today AND time = :nextMinute ORDER BY user_id",
		nativeQuery = true)
	List<GoogleQuery> getNextMinuteQueries(@Param("today") String day, @Param("nextMinute") String minute);
	
}
