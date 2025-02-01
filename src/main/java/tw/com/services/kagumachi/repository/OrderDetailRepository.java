package tw.com.services.kagumachi.repository;

import java.time.LocalDate;
import java.util.List;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tw.com.services.kagumachi.dto.OrderAllMonthDto;
import tw.com.services.kagumachi.model.Order;
import tw.com.services.kagumachi.model.OrderDetail;
@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
	List<OrderDetail> findByOrder_Orderid(@Param("orderId") Integer orderId);
	
	List<OrderDetail> findByOrder_OrderdateBetween(@Param("startDate") LocalDate startDate,
			@Param("endDate") LocalDate endDate);
	
	@Query(value="SELECT DATE_FORMAT(o.orderdate, '%m') AS orderdate, SUM(od.quantity) AS quantity "
			+ "FROM orders o "
			+ "JOIN orderdetails od ON o.orderid = od.orderid "
			+ "WHERE YEAR(o.orderdate) = :year "
			+ "GROUP BY orderdate "
			+ "ORDER BY orderdate", nativeQuery = true)
	List<OrderAllMonthDto> findByOrder_Orderdate(@Param("year") Integer year);


	@Modifying
	@Transactional
	@Query("DELETE FROM OrderDetail od WHERE od.order.orderid IN :orderIds")
	void deleteByOrderIds(@Param("orderIds") List<Integer> orderIds);

}