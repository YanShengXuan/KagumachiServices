package tw.com.services.kagumachi.controller;

import org.hibernate.annotations.WhereJoinTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.com.services.kagumachi.dto.ProductDTO;
import tw.com.services.kagumachi.model.MainCategory;
import tw.com.services.kagumachi.model.Product;
import tw.com.services.kagumachi.model.SubCategory;
import tw.com.services.kagumachi.model.Suppliers;
import tw.com.services.kagumachi.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    //新增商品
    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody ProductDTO productDTO) {
        Product savedProduct = productService.addProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }
    //查詢商品 包含照片 廠商 顏色
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
    //查詢主類別
    @GetMapping("/main")
    public ResponseEntity<List<MainCategory>> getAllMainCategories() {
        List<MainCategory> mainCategories = productService.getAllMainCategories();
        return ResponseEntity.ok(mainCategories);
    }
    // 查詢符合主類別的副類別
    @GetMapping("/sub")
    public ResponseEntity<List<SubCategory>> getSubCategoriesByMainCategoryId(
            @RequestParam Long mainCategoryId) {
        List<SubCategory> subCategories = productService.getSubCategoriesByMainCategoryId(mainCategoryId);
        return ResponseEntity.ok(subCategories);
    }

    //廠商查詢
    @GetMapping("/searchSuppliers")
    public ResponseEntity<List<Suppliers>> searchSuppliersByName() {
        List<Suppliers> suppliers = productService.getAllSuppliers();
        return ResponseEntity.ok(suppliers);
    }

    // 更新商品
    @PutMapping("/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(
            @PathVariable("productId") Integer productId,
            @RequestBody ProductDTO productDTO
    ) {
        try {
            // 調用 Service 的更新方法
            Product updatedProduct = productService.updateProduct(productId, productDTO);

            // 將更新後的實體轉換為 DTO 返回
            ProductDTO updatedProductDTO = new ProductDTO();
            updatedProductDTO.setProductid(updatedProduct.getProductid());
            updatedProductDTO.setProductname(updatedProduct.getProductname());
            updatedProductDTO.setProductdescription(updatedProduct.getProductdescription());
            updatedProductDTO.setWidth(updatedProduct.getWidth());
            updatedProductDTO.setHeight(updatedProduct.getHeight());
            updatedProductDTO.setDepth(updatedProduct.getDepth());
            updatedProductDTO.setUnitprice(updatedProduct.getUnitprice());
            updatedProductDTO.setDiscountprice(updatedProduct.getDiscountprice());
            updatedProductDTO.setProductcost(updatedProduct.getProductcost());
            updatedProductDTO.setStatus(updatedProduct.getStatus());
            updatedProductDTO.setUnitsold(updatedProduct.getUnitsold());
            updatedProductDTO.setRating(updatedProduct.getRating());
            updatedProductDTO.setReviewcount(updatedProduct.getReviewcount());
            updatedProductDTO.setUpdateat(updatedProduct.getUpdateat());

            return ResponseEntity.ok(updatedProductDTO);

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/delete/{productid}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productid) {
        try {
            productService.deleteProductById(productid);
            return ResponseEntity.ok("商品刪除成功！");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("刪除商品時發生錯誤: " + e.getMessage());
        }
    }

    @DeleteMapping("/deletecolor/{colorId}")
    public ResponseEntity<String> deleteProductColor(@PathVariable Integer colorId) {
        try {
            productService.deleteProductColorById(colorId);
            return ResponseEntity.ok("顏色刪除成功！");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("刪除顏色失敗: " + e.getMessage());
        }
    }



}
