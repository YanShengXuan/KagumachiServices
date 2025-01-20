package tw.com.services.kagumachi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tw.com.services.kagumachi.model.SubCategory;
@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {
	
	@Query("SELECT s.categoryname FROM SubCategory s")
	List<String> findAllCategoryNames();
	
	SubCategory findByCategoryname(String categoryname);
}
