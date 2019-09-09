package wgblackmon.mongodbreactivestockquoteservice.repositories;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;

import reactor.core.publisher.Flux;
import wgblackmon.mongodbreactivestockquoteservice.domain.Quote;

/**
 * 
 * @author wgb 9/8/2019
 *
 */

public interface QuoteRepository extends ReactiveMongoRepository<Quote, String> {
	
	 @Tailable
	 Flux<Quote> findWithTailableCursorBy(); //must be capped collection
}