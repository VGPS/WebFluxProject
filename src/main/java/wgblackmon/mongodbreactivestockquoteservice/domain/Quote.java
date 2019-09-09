package wgblackmon.mongodbreactivestockquoteservice.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * @author wgb 9/8/2019 
 */
// @Data
@Document
public class Quote {

    @Id
    private String id;
    private String ticker;
    private BigDecimal price;
    private Instant instant;
    
    
    
    
	public Quote(String id, String ticker, BigDecimal price, Instant instant) {
		super();
		this.id = id;
		this.ticker = ticker;
		this.price = price;
		this.instant = instant;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Instant getInstant() {
		return instant;
	}
	public void setInstant(Instant instant) {
		this.instant = instant;
	}
	@Override
	public String toString() {
		return "Quote [id=" + id + ", ticker=" + ticker + ", price=" + price + ", instant=" + instant + "]";
	}
	
	
	
}

