package tw.com.services.kagumachi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.services.kagumachi.model.Product;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	//by大瑋
	List<Product> findByProductnameContaining(String productname);

    @EntityGraph(attributePaths = {"mainCategory", "subCategory", "supplier"})
    List<Product> findAll(); // 查詢所有商品及其關聯資料
}
