package tw.com.services.kagumachi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.services.kagumachi.model.ProductImage;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {


    // 一個 ProductImage
    Optional<ProductImage> findByProduct_ProductidAndProductColor_Colorsid(Integer productid, Integer colorsid);

    // 多個 ProductImage
    List<ProductImage> findAllByProduct_ProductidAndProductColor_Colorsid(Integer productid, Integer colorsid);
}
