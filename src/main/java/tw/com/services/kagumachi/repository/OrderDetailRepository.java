package tw.com.services.kagumachi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.com.services.kagumachi.model.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {}
