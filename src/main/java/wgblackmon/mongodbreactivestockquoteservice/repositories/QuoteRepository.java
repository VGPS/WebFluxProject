package wgblackmon.mongodbreactivestockquoteservice.repositories;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import wgblackmon.mongodbreactivestockquoteservice.domain.Quote;

public interface QuoteRepository extends ReactiveMongoRepository<Quote, String> {
}