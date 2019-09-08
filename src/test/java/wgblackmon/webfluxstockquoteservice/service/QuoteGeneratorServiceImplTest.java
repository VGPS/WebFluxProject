package wgblackmon.webfluxstockquoteservice.service;


import guru.springframework.webfluxstockquoteservice.model.Quote;
import guru.springframework.webfluxstockquoteservice.service.QuoteGeneratorServiceImpl;

import org.junit.Before;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;


public class QuoteGeneratorServiceImplTest {
	
	   QuoteGeneratorServiceImpl quoteGeneratorService = new QuoteGeneratorServiceImpl();
	   
	   @Before
	   public void setUp() throws Exception {
		   
		   // get quoteFlux of quotes
		   Flux<Quote> quoteFlux = quoteGeneratorService.fetchQuoteStream(Duration.ofMillis(1L));
	
		   /**
		    * take()
		    * Take only the first N values from this Flux, if available. 
		    */
		   quoteFlux.take(2200)
		   /**Subscribe a Consumer to this Flux that will consume all theelements in the sequence. 
		    * It will request an unbounded demand (Long.MAX_VALUE). 
		    * subscribe
		    * 
		    */
		   	.subscribe(System.out::println);
		   
	   } // end setUp()
	   
	   
	   

}	// end class
