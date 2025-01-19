package tw.com.services.kagumachi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.com.services.kagumachi.model.Product;
import tw.com.services.kagumachi.model.ProductColor;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductColorRepository extends JpaRepository<ProductColor, Integer> {
    List<ProductColor> findByProduct_Productid(Integer productid);

    List<ProductColor> findAllByProduct_ProductidAndColorname(Integer productid, String colorname);


    Optional<ProductColor> findByProduct_ProductidAndColorname(Integer productid, String colorname);
}
