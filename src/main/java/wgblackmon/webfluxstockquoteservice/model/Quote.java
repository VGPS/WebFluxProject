package wgblackmon.webfluxstockquoteservice.model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.Instant;
import java.util.function.IntPredicate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Quote {

    private static final MathContext MATH_CONTEXT = new MathContext(2);

    private String ticker;
    private BigDecimal price;
    private Instant instant;

    public Quote() {}
    
    public Quote(String ticker, BigDecimal price) {
        this.ticker = ticker;
        this.price = price;
    }

    public Quote(String ticker, Double price) {
        this.ticker = ticker;
        this.price = new BigDecimal(price, MATH_CONTEXT);
    }

	public void setInstant(Instant now) {
		this.instant = now;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public String getTicker() {
		return this.ticker;
	}

	
} // end class
