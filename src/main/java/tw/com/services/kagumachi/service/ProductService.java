package tw.com.services.kagumachi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;
import tw.com.services.kagumachi.dto.ProductDTO;
import tw.com.services.kagumachi.model.*;
import tw.com.services.kagumachi.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductColorRepository productColorRepository;

    @Autowired
    private ProductImageRepository productImageRepository;

    @Autowired
    private SuppliersRepository suppliersRepository;

    public Product addProduct(ProductDTO productDTO) {
        // 1. 將 ProductDTO 映射為 Product
        Product product = new Product();
        product.setProductname(productDTO.getProductname());
        product.setProductdescription(productDTO.getProductdescription());
        product.setWidth(productDTO.getWidth());
        product.setHeight(productDTO.getHeight());
        product.setDepth(productDTO.getDepth());
        product.setUnitprice(productDTO.getUnitprice());
        product.setDiscountprice(productDTO.getDiscountprice());
        product.setProductcost(productDTO.getProductcost());
        product.setStatus(productDTO.getStatus());
        product.setUnitsold(productDTO.getUnitsold());
        product.setRating(productDTO.getRating());
        product.setReviewcount(productDTO.getReviewcount());
        product.setUpdateat(productDTO.getUpdateat());

        // 處理 Supplier
        if (productDTO.getSupplierid() != null) {
            Suppliers suppliers = new Suppliers();
            suppliers.setSupplierid(productDTO.getSupplierid());
            product.setSupplier(suppliers);

        }

        // 處理 MainCategory 和 SubCategory
        if (productDTO.getMaincategoryid() != null) {
            MainCategory mainCategory = new MainCategory();
            mainCategory.setMaincategoryid(productDTO.getMaincategoryid());
            product.setMainCategory(mainCategory);
        }

        if (productDTO.getSubcategoryid() != null) {
            SubCategory subCategory = new SubCategory();
            subCategory.setSubcategoryid(productDTO.getSubcategoryid());
            product.setSubCategory(subCategory);
        }

        // 2. 儲存 Product
        product = productRepository.save(product);

        // 3. 處理 ProductColor
        if (productDTO.getProductColors() != null) {
            for (ProductDTO.ProductColorDTO colorDTO : productDTO.getProductColors()) {
                ProductColor color = new ProductColor();
                color.setProduct(product);
                color.setColorname(colorDTO.getColorname());
                color.setStock(colorDTO.getStock());
                color.setMinstock(colorDTO.getMinstock());
                color.setUpdateat(colorDTO.getUpdateat());

                // 儲存 ProductColor
                color = productColorRepository.save(color);

                // 4. 處理 ProductImage
                if (colorDTO.getProductImages() != null) {
                    for (ProductDTO.ProductImageDTO imageDTO : colorDTO.getProductImages()) {
                        ProductImage image = new ProductImage();
                        image.setProduct(product);
                        image.setProductColor(color);
                        image.setImageurl(imageDTO.getImageurl());
                        image.setIsprimary(imageDTO.getIsprimary());
                        image.setUpdatedat(imageDTO.getUpdatedat());

                        // 儲存 ProductImage
                        productImageRepository.save(image);
                    }
                }
            }
        }

        // 返回儲存的 Product
        return product;
    }

    public List<ProductDTO> getAllProducts() {
        // 查詢所有商品
        List<Product> products = productRepository.findAll();

        // 將商品轉換為 DTO
        return products.stream().map(product -> {
            ProductDTO dto = new ProductDTO();
            dto.setProductid(product.getProductid());
            dto.setProductname(product.getProductname());
            dto.setProductdescription(product.getProductdescription());
            dto.setWidth(product.getWidth());
            dto.setHeight(product.getHeight());
            dto.setDepth(product.getDepth());
            dto.setUnitprice(product.getUnitprice());
            dto.setDiscountprice(product.getDiscountprice());
            dto.setProductcost(product.getProductcost());
            dto.setStatus(product.getStatus());
            dto.setUnitsold(product.getUnitsold());
            dto.setRating(product.getRating());
            dto.setReviewcount(product.getReviewcount());
            dto.setUpdateat(product.getUpdateat());

            // 設置 Supplier
            ProductDTO.SupplierDTO supplierDTO = new ProductDTO.SupplierDTO();
            supplierDTO.setSupplierid(product.getSupplier().getSupplierid());
            supplierDTO.setName(product.getSupplier().getName());
            supplierDTO.setAddress(product.getSupplier().getAddress());
            supplierDTO.setPhone(product.getSupplier().getPhone());
            supplierDTO.setContact(product.getSupplier().getContact());
            supplierDTO.setStatus(product.getSupplier().getStatus());

            dto.setSuppliers(supplierDTO);

            // 設置 MainCategory 和 SubCategory
            if (product.getMainCategory() != null) {
                ProductDTO.MainCategoryDTO mainCategory = new ProductDTO.MainCategoryDTO();
                mainCategory.setMaincategoryid(product.getMainCategory().getMaincategoryid());
                mainCategory.setCategoryname(product.getMainCategory().getCategoryname());
                mainCategory.setStatus(product.getMainCategory().getStatus());
                dto.setMainCategory(mainCategory);
            }

            if (product.getSubCategory() != null) {
                ProductDTO.SubCategoryDTO subCategory = new ProductDTO.SubCategoryDTO();
                subCategory.setSubcategoryid(product.getSubCategory().getSubcategoryid());
                subCategory.setCategoryname(product.getSubCategory().getCategoryname());
                subCategory.setStatus(product.getSubCategory().getStatus());
                dto.setSubCategory(subCategory);
            }

            // 設置 ProductColors
            List<ProductColor> colors = productColorRepository.findByProduct_Productid(product.getProductid());
            List<ProductDTO.ProductColorDTO> colorDTOs = colors.stream().map(color -> {
                ProductDTO.ProductColorDTO colorDTO = new ProductDTO.ProductColorDTO();
                colorDTO.setColorsid(color.getColorsid());
                colorDTO.setColorname(color.getColorname());
                colorDTO.setStock(color.getStock());
                colorDTO.setMinstock(color.getMinstock());
                colorDTO.setUpdateat(color.getUpdateat());

                // 查詢顏色相關的圖片
                List<ProductImage> images = productImageRepository.findByProduct_ProductidAndProductColor_Colorsid(
                        product.getProductid(), color.getColorsid());
                List<ProductDTO.ProductImageDTO> imageDTOs = images.stream().map(image -> {
                    ProductDTO.ProductImageDTO imageDTO = new ProductDTO.ProductImageDTO();
                    imageDTO.setImageid(image.getImageid());
                    imageDTO.setImageurl(image.getImageurl());
                    imageDTO.setIsprimary(image.getIsprimary());
                    imageDTO.setUpdatedat(image.getUpdatedat());
                    return imageDTO;
                }).collect(Collectors.toList());

                colorDTO.setProductImages(imageDTOs);
                return colorDTO;
            }).collect(Collectors.toList());

            dto.setProductColors(colorDTOs);
            return dto;
        }).collect(Collectors.toList());
    }
}