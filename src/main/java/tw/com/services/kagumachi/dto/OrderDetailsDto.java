package tw.com.services.kagumachi.dto;

public class OrderDetailsDto {
	private Integer orderdetailid;
	private String productname;
	private String colorname;
	private Integer price;
	private Integer quantity;
	private String imageurl;
	private Integer productid;
	private Integer colorsid;
	private Integer orderid;
	
	public Integer getOrderdetailid() {
		return orderdetailid;
	}
	public void setOrderdetailid(Integer orderdetailid) {
		this.orderdetailid = orderdetailid;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getColorname() {
		return colorname;
	}
	public void setColorname(String colorname) {
		this.colorname = colorname;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	public Integer getProductid() {
		return productid;
	}
	public void setProductid(Integer productid) {
		this.productid = productid;
	}
	public Integer getColorsid() {
		return colorsid;
	}
	public void setColorsid(Integer colorsid) {
		this.colorsid = colorsid;
	}
	public Integer getOrderid() {
		return orderid;
	}
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	
}
