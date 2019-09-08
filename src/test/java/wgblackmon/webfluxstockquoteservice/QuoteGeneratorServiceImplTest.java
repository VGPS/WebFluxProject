package wgblackmon.webfluxstockquoteservice;


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
	  public void setUp() throws Exception {}
	  
	  @Test
	  public void fetchQuoteStream() throws Exception {
		  
		  // get quoteFlux of quotes
		  Flux<Quote> quoteFlux = quoteGeneratorService.fetchQuoteStream(Duration.ofMillis(1L));
		  
		  quoteFlux.take(2200)
		  	.subscribe(System.out::println);
		  
	  }	// end fetchQuoteStream()
	  
	  
	  
	  @Test
	  public void fetchQuoteStreamCountDown() throws Exception {
		  
		  // get quoteFlux of quotes
		  Flux<Quote> quoteFlux = quoteGeneratorService.fetchQuoteStream(Duration.ofMillis(100L));
		  
		  // subscriber lambda
		  Consumer<Quote> println = System.out::println;
		  
		  // error handler labmda\
		  Consumer<Throwable> errorHandler = e -> System.out.println("Some error occurred");
		  
		  // set Countdown latch to 1 
		  CountDownLatch countDownLatch = new CountDownLatch(1);
		  
		  // Runnable lambda called on complete, count down latch
		  Runnable allDone = () -> countDownLatch.countDown();
		  
		  // higher value = faster run
		  quoteFlux.take(30)
		  	.subscribe(println, errorHandler, allDone);
		  
		  /**
		    Causes the current thread to wait until the latch has counted down to zero, unless the thread is interrupted. 
			If the current count is zero then this method returns immediately. 
		   */
		  countDownLatch.await();
		  
	  }	// end fetchQuoteStreamCountDown()
}	// end class
