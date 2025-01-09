package tw.com.services.kagumachi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.services.kagumachi.model.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {}
