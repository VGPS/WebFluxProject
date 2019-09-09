package wgblackmon.webfluxstockquoteservice;


import wgblackmon.webfluxstockquoteservice.model.Quote;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.concurrent.CountDownLatch;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 
 * @author wgblackmon 9/8/2019
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebfluxStockQuoteServiceApplicationTest {
	
	  @Autowired //spring will inject a configured webTestClient
	  private WebTestClient webTestClient;
	  
	  
	  // successful 
	  @Ignore
	  public void testFetchQuotes() {
		  webTestClient
          .get()
          .uri("/quotes?size=20") //set size = 20
          .accept(MediaType.APPLICATION_JSON)
          .exchange()
          .expectStatus().isOk()
          .expectHeader().contentType(MediaType.APPLICATION_JSON)
          .expectBodyList(Quote.class)
          .hasSize(20)
          .consumeWith(allQuotes -> {
              assertThat(allQuotes.getResponseBody())
                      .allSatisfy(quote -> assertThat(quote.getPrice()).isPositive());

              assertThat(allQuotes.getResponseBody()).hasSize(20);
          } );
		  
		  System.out.println("testFetchQuotes() - Test Complete"); 
	 }	// end testFetchQuotes()
	  
	  
	 // quotes are null 
	 @Test
	 public void testStreamQuotes() throws InterruptedException {
		 /**
		    CountDownLatch - 
		    A synchronization aid that allows one or more threads to wait until a set of operations being performed in other threads completes. 
			A CountDownLatch is initialized with a given count.The await methods block until the current count reaches
			zero due to invocations of the countDown method, after whichall waiting threads are released and any subsequent invocations of 
			await return immediately. This is a one-shot phenomenon-- the count cannot be reset. If you need a version that resets the count, 
			consider using a CyclicBarrier. 
		  **/
		 CountDownLatch countDownLatch = new CountDownLatch(10);

        webTestClient
                .get()
                .uri("/quotes")
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .exchange()
                .returnResult(Quote.class)
                .getResponseBody()
                .take(10)
                .subscribe(quote -> {			// quotes are null
                    assertThat(quote.getPrice()).isPositive();

                    countDownLatch.countDown();
                    System.out.println("countDownLatch is: " + countDownLatch.getCount());
                });

	        countDownLatch.await();
		 
		 System.out.println("testStreamQuotes() - Test Complete"); 
		 
		 
		 
	 }	// end testStreamQuotes()
	  

}	// end class
