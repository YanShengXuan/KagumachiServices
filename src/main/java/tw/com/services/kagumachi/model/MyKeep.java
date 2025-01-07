package tw.com.services.kagumachi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "MyKeep")
public class MyKeep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer myKeepId;

    @ManyToOne
    @JoinColumn(name = "memberId", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private Product product;

    public Integer getMyKeepId() {
        return myKeepId;
    }

    public void setMyKeepId(Integer myKeepId) {
        this.myKeepId = myKeepId;
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
}