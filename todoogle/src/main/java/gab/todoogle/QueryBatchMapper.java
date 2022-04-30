package gab.todoogle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class QueryBatchMapper {
	
	public HashMap< String, ArrayList<String> > groupByReceiver(List<GoogleQuery> queryBatch) {			// what we achieve is a number of themes mapped to an email address like
		var emailReceiversMap = new HashMap< String, ArrayList<String> >();								// ( "abc@gmail.com" : { "why don't crocodiles fly" , "how to cook mac&cheese" , "news 26.04" } )
																										// ( "def@gmail.com" : { "..." , "..." } )
		queryBatch.forEach(query -> {																	// etc.
			if(!emailReceiversMap.containsKey(query.getUser().getEmail())) {
				emailReceiversMap.put(query.getUser().getEmail(), new ArrayList<>());
			}
			emailReceiversMap.get(query.getUser().getEmail()).add(query.getQuery());
		});
		
		return emailReceiversMap;
	}
	
}
