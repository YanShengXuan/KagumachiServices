package tw.com.services.kagumachi.dto;

import java.math.BigDecimal;

public class OrderAllMonthDto {
	private String orderdate;
    private BigDecimal quantity;
    
    public OrderAllMonthDto(String orderdate, BigDecimal quantity) {
    	this.orderdate = orderdate;
    	this.quantity = quantity;
    }
    
	public String getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
    
}
