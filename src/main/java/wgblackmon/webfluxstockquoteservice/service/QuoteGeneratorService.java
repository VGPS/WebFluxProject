package wgblackmon.webfluxstockquoteservice.service;

import wgblackmon.webfluxstockquoteservice.model.Quote;
import reactor.core.publisher.Flux;
import java.time.Duration;

/**
 * 
 * @author wgb1454 9/8/2019
 *
 */

public interface QuoteGeneratorService {
	
	Flux<Quote> fetchQuoteStream(Duration period);

}
