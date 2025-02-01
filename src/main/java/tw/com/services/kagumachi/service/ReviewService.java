package tw.com.services.kagumachi.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.services.kagumachi.dto.ReviewDto;
import tw.com.services.kagumachi.model.Member;
import tw.com.services.kagumachi.model.Order;
import tw.com.services.kagumachi.model.Product;
import tw.com.services.kagumachi.model.ProductColor;
import tw.com.services.kagumachi.model.Review;
import tw.com.services.kagumachi.repository.MemberRepository;
import tw.com.services.kagumachi.repository.OrderRepository;
import tw.com.services.kagumachi.repository.ProductColorRepository;
import tw.com.services.kagumachi.repository.ProductRepository;
import tw.com.services.kagumachi.repository.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductColorRepository productColorRepository;
	
	public ReviewDto getReviews(Integer productId, Integer colorsId){
		Review review = reviewRepository.findByProduct_ProductidAndProductcolor_Colorsid(productId, colorsId);
		
		ReviewDto reviewDto = new ReviewDto();
		if (review != null) {
			reviewDto.setOrderid(review.getOrder().getOrderid());
			reviewDto.setColorsid(review.getProductcolor().getColorsid());
			reviewDto.setRating(review.getRating());
			reviewDto.setContent(review.getContent());
			reviewDto.setIssubmitted(review.getIssubmitted());
			}
		return reviewDto;
	}
	
	public void addReviews(List<Map<String, Object>> reviewsPayload) {
		for (Map<String, Object> payload : reviewsPayload) {
			Review review = new Review();
			
			Integer productId = (Integer) payload.get("productId");
			Product product = productRepository.findById(productId)
			        .orElseThrow(() -> new RuntimeException("找不到對應的商品 ID: " + productId));

			Integer colorsId = (Integer) payload.get("colorsId");
			ProductColor productcolor = productColorRepository.findById(colorsId)
			        .orElseThrow(() -> new RuntimeException("找不到對應的顏色 ID: " + colorsId));

			Integer memberId = (Integer) payload.get("memberId");
			Member member = memberRepository.findById(memberId)
					.orElseThrow(() -> new RuntimeException("找不到對應的會員 ID: " + memberId));
			
			Integer orderId = (Integer)payload.get("orderId");
			Order order = orderRepository.findById(orderId)
			        .orElseThrow(() -> new RuntimeException("找不到對應的訂單 ID: " + orderId));
			
			review.setMember(member);
			review.setProduct(product);
			review.setProductcolor(productcolor);
			review.setOrder(order);
			review.setContent((String) payload.get("content"));
			review.setRating((Integer) payload.get("rating"));
			review.setIssubmitted((Boolean)payload.get("isSubmitted"));
            
			reviewRepository.save(review);
			
			updateProductRatingAndReviewCount(product);
		}
	}
	
	public void updateProductRatingAndReviewCount(Product product) {
		List<Review> reviews = reviewRepository.findByProduct(product);
		
		int reviewCount = reviews.size();
		int totalRating = reviews.stream()
	        .mapToInt(Review::getRating)
	        .sum();
	    
		double newRating = (reviewCount > 0) ? Math.ceil((totalRating / (double) reviewCount) * 10) / 10 : 0;
	    
	    product.setRating(newRating);
	    product.setReviewcount(reviewCount);
	    
	    productRepository.save(product);
	}
}
