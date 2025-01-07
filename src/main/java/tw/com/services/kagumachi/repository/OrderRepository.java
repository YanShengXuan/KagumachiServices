package tw.com.services.kagumachi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.com.services.kagumachi.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {}
