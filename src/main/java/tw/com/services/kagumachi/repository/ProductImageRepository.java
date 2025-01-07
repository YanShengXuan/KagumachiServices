package tw.com.services.kagumachi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.com.services.kagumachi.model.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {}
