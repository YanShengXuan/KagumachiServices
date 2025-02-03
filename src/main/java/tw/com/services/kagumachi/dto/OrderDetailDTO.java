package tw.com.services.kagumachi.dto;

public class OrderDetailDTO {
	private int productId;
	private int colorsId;
	private int quantity;
	private int price;
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getColorsId() {
		return colorsId;
	}
	public void setColorsId(int colorsId) {
		this.colorsId = colorsId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "productId: " + productId + ", colorsId: " + colorsId + ", quantity: " + quantity;
	}
	
	
}
