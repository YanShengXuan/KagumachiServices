package tw.com.services.kagumachi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.services.kagumachi.model.Product;
import tw.com.services.kagumachi.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // 查詢所有產品
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

}
