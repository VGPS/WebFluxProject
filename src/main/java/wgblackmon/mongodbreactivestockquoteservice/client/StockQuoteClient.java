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
@Slf4j
@Setter
@Component
/**
  	get guru values from applicationl.properties file
 	guru.host=localhost
	guru.port=8081
	guru.path=/quotes
 *
 */
@ConfigurationProperties("guru")
public class StockQuoteClient {

    private String host;
    private String port;
    private String path;
    
    
    public StockQuoteClient() {}

    public StockQuoteClient(String host, String port, String path) {
		super();
		this.host = host;
		this.port = port;
		this.path = path;
	}
	

	public Flux<Quote> getQuoteStream(){

        String url = "http://" + host + port;

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

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
    
}	// end class    
    