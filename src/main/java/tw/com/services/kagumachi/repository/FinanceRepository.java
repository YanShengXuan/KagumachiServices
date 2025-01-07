package tw.com.services.kagumachi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.com.services.kagumachi.model.Finance;

@Repository
public interface FinanceRepository extends JpaRepository<Finance, Integer> {
}