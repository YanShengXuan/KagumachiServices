package tw.com.services.kagumachi.repository;



import java.util.List;


import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import tw.com.services.kagumachi.model.Product;

import tw.com.services.kagumachi.model.Member;

import tw.com.services.kagumachi.model.Review;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
	Review findByProduct_ProductidAndProductcolor_Colorsid(Integer productId, Integer colorId);

	
	List<Review> findByProduct(Product product);


	List<Review> findByProduct_Productid(Integer productid);

	@Modifying
	@Transactional
	@Query("DELETE FROM Review r WHERE r.member.memberid = :memberId")
	void deleteByMemberId(@Param("memberId") Integer memberId);

}
