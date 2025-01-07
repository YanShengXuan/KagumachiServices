package tw.com.services.kagumachi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.com.services.kagumachi.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {}
