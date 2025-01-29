package tw.com.services.kagumachi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tw.com.services.kagumachi.model.ProductImage;
@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {

    // 一個 ProductImage
    Optional<ProductImage> findByProduct_ProductidAndProductColor_Colorsid(Integer productid, Integer colorsid);

    // 多個 ProductImage
    List<ProductImage> findAllByProduct_ProductidAndProductColor_Colorsid(Integer productid, Integer colorsid);

    List<ProductImage> findAllByProductColor_Colorsid(Integer colorsid);

    List<ProductImage> findAllByProduct_Productid(Integer productid);
  
	Optional<ProductImage> findByProduct_ProductidAndProductColor_ColorsidAndIsprimary(Integer productid,
			Integer colorsId, boolean b);
}
