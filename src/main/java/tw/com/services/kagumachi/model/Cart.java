package tw.com.services.kagumachi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name = "Carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartsid;

    private Integer quantity;

    private Boolean ispurchase;

    public Integer getCartsid() {
        return cartsid;
    }

    public void setCartsid(Integer cartsid) {
        this.cartsid = cartsid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getIspurchase() {
        return ispurchase;
    }

    public void setIspurchase(Boolean ispurchase) {
        this.ispurchase = ispurchase;
    }

    @ManyToOne
    @JoinColumn(name = "memberid")
    @JsonManagedReference
    private Member member;
    public Member getMember() {
        return member;
    }
    public void setMember(Member member) {
        this.member = member;
    }

    @ManyToOne
    @JoinColumn(name = "productid")
    @JsonManagedReference
    private Product product;
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }

    @ManyToOne
    @JoinColumn(name = "colorid") // 資料庫Carts的欄位就叫colorid，別再改了！by HongJun
    @JsonManagedReference
    private ProductColor productColor;

    public ProductColor getProductColor() {
        return productColor;
    }

    public void setProductColor(ProductColor productColor) {
        this.productColor = productColor;
    }

}