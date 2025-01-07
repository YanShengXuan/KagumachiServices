package tw.com.services.kagumachi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.com.services.kagumachi.model.Sales;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Integer> {
}