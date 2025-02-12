package tw.com.services.kagumachi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tw.com.services.kagumachi.model.MainCategory;
@Repository
public interface MainCategoryRepository extends JpaRepository<MainCategory, Integer> {
}
