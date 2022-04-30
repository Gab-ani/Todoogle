package gab.todoogle;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class QueryBatchMapperTest {

	@Test
	void themesCombiningTest() {
		QueryBatchMapper testMapper = new QueryBatchMapper();
		var map = testMapper.groupByReceiver(createTestBatch());
		
		assertTrue( map.get("email1").contains("query1Content") &&
					map.get("email1").contains("query2Content") &&
					map.get("email1").contains("query3Content") &&
					!map.get("email1").contains("query4Content"));
		
		assertTrue( map.get("email2").contains("query4Content") &&
					!map.get("email2").contains("query2Content") &&
					!map.get("email2").contains("query3Content") &&
					!map.get("email2").contains("query1Content"));
	}
	
	ArrayList<GoogleQuery> createTestBatch() {						// 2 receivers, first has 3 themes, second has 1
		var testBatch = new ArrayList<GoogleQuery>();
		
		var query1 = new GoogleQuery();
		query1.setUser(new User("username1", "password", "email1"));
		query1.setQuery("query1Content");
		
		var query2 = new GoogleQuery();
		query2.setUser(new User("username1", "password", "email1"));
		query2.setQuery("query2Content");
		
		var query3 = new GoogleQuery();
		query3.setUser(new User("username1", "password", "email1"));
		query3.setQuery("query3Content");
		
		var query4 = new GoogleQuery();
		query4.setUser(new User("username2", "password", "email2"));
		query4.setQuery("query4Content");
		
		
		
		testBatch.add(query1);
		testBatch.add(query2);
		testBatch.add(query3);
		testBatch.add(query4);
		return testBatch;
	}
	
}
