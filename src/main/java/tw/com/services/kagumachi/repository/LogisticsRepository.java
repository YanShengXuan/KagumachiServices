package tw.com.services.kagumachi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tw.com.services.kagumachi.model.Logistics;

@Repository
public interface LogisticsRepository extends JpaRepository<Logistics, Integer> {
	
	@Query("SELECT l FROM Logistics l")
    List<Logistics> findAllLogistics();
}