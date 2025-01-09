package tw.com.services.kagumachi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Mykeep")
public class MyKeep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mykeepid;

    @ManyToOne
    @JoinColumn(name = "memberid", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "productid", nullable = false)
    private Product product;

    public Integer getMykeepid() {
        return mykeepid;
    }

    public void setMykeepid(Integer mykeepid) {
        this.mykeepid = mykeepid;
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