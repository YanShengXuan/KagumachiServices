package tw.com.services.kagumachi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tw.com.services.kagumachi.model.Cart;
import java.util.*;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
	List<Cart> findByMember_Memberid(@Param("memberId") Integer memberId);
    Optional<Cart> findByMember_MemberidAndProduct_ProductidAndProductColor_Colorsid(Integer memberid, Integer productid, Integer colorsid);
}
