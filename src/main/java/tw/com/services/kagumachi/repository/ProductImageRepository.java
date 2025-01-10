package tw.com.services.kagumachi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.services.kagumachi.model.ProductImage;

import java.util.Optional;
@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {
    Optional<ProductImage> findByProduct_ProductidAndProductColor_Colorsid(Integer productid, Integer colorsid);
}
