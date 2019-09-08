package wgblackmon.webfluxstockquoteservice.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

/**
 * 
 * @author wgblackmon 9/8/2019
 *
 */

/**
 * @Configuration:
 * Indicates that a class declares one or more @Bean methods andmay be processed by the Spring container to generate bean definitions andservice requests 
 * for those beans at runtime,
 *
 */
@Configuration
public class QuoteRouter {
	
	@Bean
	public RouterFunction<ServerResponse> route(QuoteHandler handler) {
		return RouterFunctions
				.route(GET("/quotes").and(accept(MediaType.APPLICATION_JSON)), handler::fetchQuotes)
				.andRoute(GET("/quotes").and(accept(MediaType.APPLICATION_OCTET_STREAM)), handler::streamQuotes);
	}

}	// end class
