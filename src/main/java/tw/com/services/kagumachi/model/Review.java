package tw.com.services.kagumachi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewid;

    @ManyToOne
    @JoinColumn(name = "memberid")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "productid")
    private Product product;

    private Integer rating;
    private String content;
    
    @ManyToOne
    @JoinColumn(name = "orderid")
    private Order order;
    
    @ManyToOne
    @JoinColumn(name = "colorsid")
    private ProductColor productcolor;
    
    private Boolean issubmitted;

    public Integer getReviewid() {
        return reviewid;
    }

    public void setReviewid(Integer reviewid) {
        this.reviewid = reviewid;
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

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public ProductColor getProductcolor() {
		return productcolor;
	}

	public void setProductcolor(ProductColor productcolor) {
		this.productcolor = productcolor;
	}

	public Boolean getIssubmitted() {
		return issubmitted;
	}

	public void setIssubmitted(Boolean issubmitted) {
		this.issubmitted = issubmitted;
	}

	

	
}
