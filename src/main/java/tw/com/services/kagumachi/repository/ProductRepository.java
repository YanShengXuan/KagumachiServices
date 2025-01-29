package tw.com.services.kagumachi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tw.com.services.kagumachi.model.Product;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	
	List<Product> findByProductnameContaining(String productname);

    @EntityGraph(attributePaths = {"mainCategory", "subCategory", "supplier"})
    List<Product> findAll();

    @EntityGraph(attributePaths = {"mainCategory", "subCategory", "supplier"})
    @Query("SELECT p FROM Product p WHERE " +
            "(COALESCE(:productname, '') = '' OR p.productname LIKE %:productname%) AND " +
            "(COALESCE(:maincategoryid, 0) = 0 OR p.mainCategory.maincategoryid = :maincategoryid) AND " +
            "(COALESCE(:subcategoryid, 0) = 0 OR p.subCategory.subcategoryid = :subcategoryid)")
    List<Product> searchProducts(
            @Param("productname") String productname,
            @Param("maincategoryid") Integer maincategoryid,
            @Param("subcategoryid") Integer subcategoryid);
    
    @Query("SELECT p FROM Product p WHERE p.mainCategory.id = :maincategoryid AND p.subCategory.id = :subcategoryid")
    List<Product> findByCategory(@Param("maincategoryid") int maincategoryid, @Param("subcategoryid") int subcategoryid);


    @Query("SELECT p FROM Product p JOIN FETCH p.mainCategory WHERE p.mainCategory.maincategoryid = :maincategoryid")
    List<Product> findByMainCategory(@Param("maincategoryid") Integer maincategoryid);

}
