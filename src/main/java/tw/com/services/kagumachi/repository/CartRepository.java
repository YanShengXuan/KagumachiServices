package tw.com.services.kagumachi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.com.services.kagumachi.model.Cart;

public interface CartRepository extends JpaRepository<Cart,Integer> {

}
