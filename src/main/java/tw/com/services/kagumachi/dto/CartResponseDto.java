package tw.com.services.kagumachi.dto;


public class CartResponseDto {
	private Integer cartsid;
	private String productname;
	private String colorname;
	private Integer quantity;
	private Integer unitprice;
	private String imageurl;
    private Boolean ispurchase;
	public Integer getCartsid() {
		return cartsid;
	}
	public void setCartsid(Integer cartsid) {
		this.cartsid = cartsid;
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
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(Integer unitprice) {
		this.unitprice = unitprice;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	public Boolean getIspurchase() {
		return ispurchase;
	}
	public void setIspurchase(Boolean ispurchase) {
		this.ispurchase = ispurchase;
	}
    
    
}
