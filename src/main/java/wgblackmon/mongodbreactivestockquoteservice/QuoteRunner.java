package wgblackmon.mongodbreactivestockquoteservice;

import wgblackmon.mongodbreactivestockquoteservice.client.StockQuoteClient;
import wgblackmon.mongodbreactivestockquoteservice.domain.Quote;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;

/**
@author wgb 9/8/2019
 
CommandLineRunner: 
Interface used to indicate that a bean should run when it is contained withina SpringApplication. 
Multiple CommandLineRunner beans can be defined within the same application context and can be ordered using the 
ordered interface or @Order annotation.
**/
// @Component
public class QuoteRunner implements CommandLineRunner {

    private final StockQuoteClient stockQuoteClient;

    public QuoteRunner(StockQuoteClient stockQuoteClient) {
        this.stockQuoteClient = stockQuoteClient;
    }

    @Override
    public void run(String... args) throws Exception {
        Flux<Quote> quoteFlux = stockQuoteClient.getQuoteStream();

        quoteFlux.subscribe(System.out::println);
    }
}