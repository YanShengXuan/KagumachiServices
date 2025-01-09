package tw.com.services.kagumachi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tw.com.services.kagumachi.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
	List<Cart> findByMember_Memberid(@Param("memberId") Integer memberId);
}
