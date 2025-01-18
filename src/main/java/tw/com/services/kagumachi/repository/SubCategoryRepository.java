package tw.com.services.kagumachi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tw.com.services.kagumachi.model.SubCategory;

import java.util.List;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {
    List<SubCategory> findByMainCategory_Maincategoryid(Long mainCategoryId);

    @Modifying
    @Query("DELETE FROM SubCategory s WHERE s.mainCategory.maincategoryid = :maincategoryid")
    void deleteByMainCategory_Maincategoryid(@Param("maincategoryid") Long maincategoryid);
}
