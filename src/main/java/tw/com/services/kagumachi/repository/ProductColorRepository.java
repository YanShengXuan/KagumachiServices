package tw.com.services.kagumachi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.com.services.kagumachi.model.ProductColor;

import java.util.List;

@Repository
public interface ProductColorRepository extends JpaRepository<ProductColor, Integer> {
    List<ProductColor> findByProduct_Productid(Integer productid);
}
