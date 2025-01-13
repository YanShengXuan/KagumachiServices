package tw.com.services.kagumachi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartsid;

    @ManyToOne
    @JoinColumn(name = "memberid")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "productid")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "colorsid")
    private ProductColor productColor;

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


    public Member getMember() {
        return member;
    }
    public void setMember(Member member) {
        this.member = member;
    }


    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }



    public ProductColor getProductColor() {
        return productColor;
    }

    public void setProductColor(ProductColor productColor) {
        this.productColor = productColor;
    }

}