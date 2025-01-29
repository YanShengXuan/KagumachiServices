package tw.com.services.kagumachi.dto;

public class ReviewDto {
    private Integer orderid;
    private Integer colorsid;
    private Integer rating;
    private String content;
    private Boolean issubmitted;
	public Integer getOrderid() {
		return orderid;
	}
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	public Integer getColorsid() {
		return colorsid;
	}
	public void setColorsid(Integer colorsid) {
		this.colorsid = colorsid;
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
	public Boolean getIssubmitted() {
		return issubmitted;
	}
	public void setIssubmitted(Boolean issubmitted) {
		this.issubmitted = issubmitted;
	}
	
    
}
