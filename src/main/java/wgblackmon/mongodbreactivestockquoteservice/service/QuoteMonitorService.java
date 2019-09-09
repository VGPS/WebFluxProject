package wgblackmon.mongodbreactivestockquoteservice.service;

import wgblackmon.mongodbreactivestockquoteservice.client.StockQuoteClient;
import wgblackmon.mongodbreactivestockquoteservice.domain.Quote;
import wgblackmon.mongodbreactivestockquoteservice.repositories.QuoteRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author wgb 9/8/2019
 */
@Service
public class QuoteMonitorService implements ApplicationListener<ContextRefreshedEvent> {

    private final StockQuoteClient stockQuoteClient;
    private final QuoteRepository quoteRepository;

    public QuoteMonitorService(StockQuoteClient stockQuoteClient, QuoteRepository quoteRepository) {
        this.stockQuoteClient = stockQuoteClient;
        this.quoteRepository = quoteRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    	
    	// get handle to quote stream
        stockQuoteClient.getQuoteStream()
                .log("quote-monitor-service")		// chatter output
                .subscribe(quote -> {				// lambda call
                	// fails here when MongoDB isn't running
                    Mono<Quote> savedQuote = quoteRepository.save(quote);	// write record

                    System.out.println("I saved a quote! Id: " +savedQuote.block().getId());
                });
    }
}