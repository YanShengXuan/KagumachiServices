package tw.com.services.kagumachi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.services.kagumachi.model.SubCategory;
@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {}
