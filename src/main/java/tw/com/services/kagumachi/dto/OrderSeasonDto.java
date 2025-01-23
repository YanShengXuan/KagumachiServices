package tw.com.services.kagumachi.dto;


public class OrderSeasonDto {
	private String categoryname;
	private Integer quantity;
	
	public OrderSeasonDto(String categoryname, Integer quantity) {
		this.categoryname = categoryname;
		this.quantity = quantity;
	}
	
	public String getCategoryname() {
		return categoryname;
	}
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
