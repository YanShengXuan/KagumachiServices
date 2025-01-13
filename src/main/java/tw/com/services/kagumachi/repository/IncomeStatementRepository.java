package tw.com.services.kagumachi.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.services.kagumachi.model.IncomeStatement;


@Repository
public interface IncomeStatementRepository extends JpaRepository<IncomeStatement, Integer> {
	List<IncomeStatement> findByTime(LocalDate time);
}