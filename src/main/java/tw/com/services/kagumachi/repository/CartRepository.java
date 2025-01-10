package tw.com.services.kagumachi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.com.services.kagumachi.model.Cart;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Integer> {
    Optional<Cart> findByMember_MemberidAndProduct_ProductidAndColor_Colorsid(Integer memberid, Integer productid, Integer colorsid);
}
