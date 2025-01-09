package tw.com.services.kagumachi.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "Productcolors")
public class ProductColor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer colorsid;

    private String colorname;
    private Integer stock;
    private Integer minstock;
    private LocalDate updateat;

    public Integer getColorsid() {
        return colorsid;
    }

    public void setColorsid(Integer colorsid) {
        this.colorsid = colorsid;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getColorname() {
        return colorname;
    }

    public void setColorname(String colorname) {
        this.colorname = colorname;
    }

    public Integer getMinstock() {
        return minstock;
    }

    public void setMinstock(Integer minstock) {
        this.minstock = minstock;
    }

    public LocalDate getUpdateat() {
        return updateat;
    }

    public void setUpdateat(LocalDate updateat) {
        this.updateat = updateat;
    }
    
    @ManyToOne
    @JoinColumn(name = "productid")
    @JsonBackReference
    private Product product;
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
//  @OneToMany(mappedBy = "productColor")
//	@JsonBackReference
//	private List<Cart> cart;
//    public List<Cart> getCarts() {
//		return cart;
//	}
//	public void setCarts(List<Cart> cart) {
//		this.cart = cart;
//	}
	
	@OneToMany(mappedBy = "productColor")
	@JsonBackReference
	private List<ProductImage> productImage;
	public List<ProductImage> getProductImage() {
		return productImage;
	}
	public void setProductImage(List<ProductImage> productImage) {
		this.productImage = productImage;
	}
}