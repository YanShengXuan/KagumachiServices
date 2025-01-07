package tw.com.services.kagumachi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartsId;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "colorId")
    private ProductColor color;

    private Integer quantity;

    private Boolean isPurchase;

    public Integer getCartsId() {
        return cartsId;
    }

    public void setCartsId(Integer cartsId) {
        this.cartsId = cartsId;
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

    public ProductColor getColor() {
        return color;
    }

    public void setColor(ProductColor color) {
        this.color = color;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getIsPurchase() {
        return isPurchase;
    }

    public void setIsPurchase(Boolean isPurchase) {
        this.isPurchase = isPurchase;
    }
}