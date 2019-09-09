package wgblackmon.mongodbreactivestockquoteservice;

import wgblackmon.mongodbreactivestockquoteservice.client.StockQuoteClient;
import wgblackmon.mongodbreactivestockquoteservice.domain.Quote;
import org.springframework.boot.CommandLineRunner;
import reactor.core.publisher.Flux;

/**
 * @author wgb 9/8/2019
 */
//@Component
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