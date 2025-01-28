package tw.com.services.kagumachi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tw.com.services.kagumachi.model.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByMember_Memberid(Integer memberid);
    
    @Query("SELECT o FROM Order o WHERE o.orderdate BETWEEN :startDate AND :endDate ORDER BY o.orderdate ASC")
    List<Order> findByDateBetween(@Param("startDate") LocalDate startDate,
			@Param("endDate") LocalDate endDate);

    @Query("SELECT o FROM Order o ORDER BY o.totalprice ASC")
    List<Order> findByDateBetweenTotalprice(@Param("startDate") LocalDate startDate,
			@Param("endDate") LocalDate endDate);
    
    @Query("SELECT o FROM Order o ORDER BY o.orderstatus ASC")
    List<Order> findByDateBetweenOrderstatus(@Param("startDate") LocalDate startDate,
			@Param("endDate") LocalDate endDate);
    
    @Query("SELECT o.orderid FROM Order o WHERE o.orderserial = :orderSerial")
    Integer findByOrderserial(@Param("orderSerial") String orderSerial);

    //Emma
    @Query("SELECT o FROM Order o WHERE o.member.memberid = :memberid ORDER BY o.orderdate DESC LIMIT 1")
    Optional<Order> findLatestOrderByMemberId(Integer memberid);
}
