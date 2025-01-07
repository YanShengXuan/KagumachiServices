package tw.com.services.kagumachi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.com.services.kagumachi.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {}
