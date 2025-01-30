package tw.com.services.kagumachi.dto;

import java.util.Date;

public class ProductReviewDTO {

    private Integer reviewid;
    private Integer productid;
    private String realname;  // 來自 Member
    private Integer rating;
    private String content;  // 修改名稱，與 Review 的 content 對應
    private Date createdAt;  // 應對應 Review 的 timestamp

    public Integer getReviewid() {
        return reviewid;
    }

    public void setReviewid(Integer reviewid) {
        this.reviewid = reviewid;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
