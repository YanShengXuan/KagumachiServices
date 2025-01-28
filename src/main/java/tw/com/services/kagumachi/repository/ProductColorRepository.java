package tw.com.services.kagumachi.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.services.kagumachi.model.ProductColor;

@Repository
public interface ProductColorRepository extends JpaRepository<ProductColor, Integer> {
    List<ProductColor> findByProduct_Productid(Integer productid);

    Optional<ProductColor> findByProduct_ProductidAndColorname(Integer productid, String colorname);
    
}
