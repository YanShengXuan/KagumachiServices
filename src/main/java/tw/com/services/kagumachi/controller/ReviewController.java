package tw.com.services.kagumachi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.com.services.kagumachi.dto.ReviewDto;
import tw.com.services.kagumachi.service.ReviewService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/reviews")
public class ReviewController {

	@Autowired
	private ReviewService reviewService; 
	
	@GetMapping
    public ResponseEntity<?> getreviews(
        @RequestParam Integer productId, 
        @RequestParam Integer colorsId){
		ReviewDto reviews = reviewService.getReviews(productId, colorsId);
		if (reviews == null) {
	        return ResponseEntity.ok(Map.of(
	                "orderid", null,
	                "rating", 0,
	                "content", "",
	                "issubmitted", null
	            ));
	    }
		return ResponseEntity.ok(reviews);
	}
	
	@PostMapping("/addreviews")
	public ResponseEntity<String> addreviews(@RequestBody List<Map<String, Object>> reviewsPayload) {
		try {
	        reviewService.addReviews(reviewsPayload);
	        return ResponseEntity.ok("評論已成功新增");
	    } catch (Exception e) {
	        System.out.println(e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("新增評論時發生錯誤");
	    }
	
	}
	
	
}
