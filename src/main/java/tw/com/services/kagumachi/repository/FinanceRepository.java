package tw.com.services.kagumachi.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tw.com.services.kagumachi.model.Finance;

@Repository
public interface FinanceRepository extends JpaRepository<Finance, Integer> {
	@Query("SELECT f FROM Finance f WHERE f.date BETWEEN :startDate AND :endDate ORDER BY f.date ASC")
	List<Finance> findByDateBetween(@Param("startDate") LocalDate startDate,
			@Param("endDate") LocalDate endDate);
	
	@Query("SELECT f FROM Finance f WHERE f.date BETWEEN :startDate AND :endDate AND f.item = :item ORDER BY f.date ASC")
	List<Finance> findByDateBetweenAndItem(@Param("startDate") LocalDate startDate,
			@Param("endDate") LocalDate endDate, @Param("item") String item);
//	@Query("INSERT INTO `finance` (`financeid`, `date`, `item`, `details`, `money`) VALUES (NULL, '2025-01-17', '收入', '刮刮樂', '3000')")
}