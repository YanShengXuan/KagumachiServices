package tw.com.services.kagumachi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orderdetails")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderdetailid;

    @ManyToOne
    @JoinColumn(name = "orderid")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "productid")
    private Product product;
    
    @ManyToOne
    @JoinColumn(name = "colorsid")
    private ProductColor productColor;

    private Integer quantity;

    public Integer getOrderdetailid() {
        return orderdetailid;
    }

    public void setOrderdetailid(Integer orderdetailid) {
        this.orderdetailid = orderdetailid;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

	public ProductColor getProductColor() {
		return productColor;
	}

	public void setProductColor(ProductColor productColor) {
		this.productColor = productColor;
	}
	
	@Override
	public String toString() {
	    return "OrderDetail{" +
	           "orderDetailId=" + orderdetailid +
	           ", orderId=" + order +
	           ", productId=" + product.getProductid() +
	           ", colorsId=" + productColor.getColorsid() +
	           ", quantity=" + quantity +
	           "}";
	}
    
}
