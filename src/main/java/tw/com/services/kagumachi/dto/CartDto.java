package tw.com.services.kagumachi.dto;


public class CartDto {
	private Integer cartsid;
	private String productname;
	private String colorname;
	private Integer quantity;
	private Integer unitprice;
	private String imageurl;
    private Boolean ispurchase;
    private Double width;
    private Double height;
    private Double depth;
    private Integer discountprice;
    private String salesname;
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
	public Double getWidth() {
		return width;
	}
	public void setWidth(Double width) {
		this.width = width;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public Double getDepth() {
		return depth;
	}
	public void setDepth(Double depth) {
		this.depth = depth;
	}
	public Integer getDiscountprice() {
		return discountprice;
	}
	public void setDiscountprice(Integer discountprice) {
		this.discountprice = discountprice;
	}
	public String getSalesname() {
		return salesname;
	}
	public void setSalesname(String salesname) {
		this.salesname = salesname;
	}
	
	
}
