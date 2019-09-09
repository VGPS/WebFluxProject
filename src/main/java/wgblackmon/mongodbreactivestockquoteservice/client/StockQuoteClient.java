package wgblackmon.mongodbreactivestockquoteservice.client;

import wgblackmon.mongodbreactivestockquoteservice.domain.Quote;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

/**
 *@author wgb 9/8/2019
 */

/**
  	get guru values from applicationl.properties file
 	guru.host=localhost
	guru.port=8081
	guru.path=/quotes
 *
 */
@Slf4j
@Setter
@Component
@ConfigurationProperties("guru")
public class StockQuoteClient {

	// these props. are NOT being pulled from application.properties file 
    private String host = "localhost";
    private String port = "8080";		// port collision with netty
    private String path = "/quotes";	// not being picked up from guru settings 
   
 
	public Flux<Quote> getQuoteStream(){

        String url = "http://" + host + ":" + port;

        System.out.println("getQuoteStream() - Url Set is: " + url);

        return WebClient.builder()
                .baseUrl(url)
                .build()
                .get()
                .uri(path)		
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .retrieve()
                .bodyToFlux(Quote.class);
    }

	
}	// end class    
    