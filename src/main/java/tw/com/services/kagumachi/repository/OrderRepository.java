package tw.com.services.kagumachi.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import tw.com.services.kagumachi.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByMember_Memberid(Integer memberid);
    
    @Query("SELECT o FROM Order o WHERE o.orderdate BETWEEN :startDate AND :endDate ORDER BY o.orderdate ASC")
    List<Order> findByDateBetween(@Param("startDate") LocalDate startDate,
			@Param("endDate") LocalDate endDate);

    @Query("SELECT o FROM Order o WHERE o.orderdate BETWEEN :startDate AND :endDate ORDER BY o.totalprice ASC")
    List<Order> findByDateBetweenTotalprice(@Param("startDate") LocalDate startDate,
			@Param("endDate") LocalDate endDate);
    
    @Query("SELECT o FROM Order o WHERE o.orderdate BETWEEN :startDate AND :endDate ORDER BY o.orderstatus DESC")
    List<Order> findByDateBetweenOrderstatus(@Param("startDate") LocalDate startDate,
			@Param("endDate") LocalDate endDate);
    
    @Query("SELECT o.orderid FROM Order o WHERE o.orderserial = :orderSerial")
    Integer findByOrderserial(@Param("orderSerial") String orderSerial);

    //Emma
    @Query("SELECT o FROM Order o WHERE o.member.memberid = :memberid ORDER BY o.orderdate DESC LIMIT 1")
    Optional<Order> findLatestOrderByMemberId(Integer memberid);

    @Query("SELECT o.orderid FROM Order o WHERE o.member.memberid = :memberId")
    List<Integer> findOrderIdsByMemberId(@Param("memberId") Integer memberId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Order o WHERE o.member.memberid = :memberId")
    void deleteByMemberId(@Param("memberId") Integer memberId);
    
    // by ChongWei
    @Query("SELECT COALESCE(SUM(o.totalprice), 0) FROM Order o WHERE o.orderdate BETWEEN :startDate AND :endDate")
    Double findTotalRevenueByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
