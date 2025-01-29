package tw.com.services.kagumachi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.services.kagumachi.model.Review;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
	Review findByProduct_ProductidAndProductcolor_Colorsid(Integer productId, Integer colorId);

	List<Review> findByProduct_Productid(Integer productid);
}
