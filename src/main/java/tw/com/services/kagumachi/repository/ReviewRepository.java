package tw.com.services.kagumachi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.services.kagumachi.model.Review;
@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
	Review findByProduct_ProductidAndProductcolor_Colorsid(Integer productId, Integer colorId);
}
