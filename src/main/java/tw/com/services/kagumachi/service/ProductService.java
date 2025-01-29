package tw.com.services.kagumachi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.services.kagumachi.dto.ProductDTO;
import tw.com.services.kagumachi.dto.ProductReviewDTO;
import tw.com.services.kagumachi.model.*;
import tw.com.services.kagumachi.repository.*;

import java.util.List;
import java.util.Optional;
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

    @Autowired
    private MainCategoryRepository mainCategoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private ReviewRepository reviewRepository;

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


            if (product.getMainCategory() != null) {
                dto.setMaincategoryid(product.getMainCategory().getMaincategoryid()); // 設置主類別 ID
                ProductDTO.MainCategoryDTO mainCategory = new ProductDTO.MainCategoryDTO();
                mainCategory.setMaincategoryid(product.getMainCategory().getMaincategoryid());
                mainCategory.setCategoryname(product.getMainCategory().getCategoryname());
                mainCategory.setStatus(product.getMainCategory().getStatus());

                if (product.getMainCategory().getSales() != null) {
                    Sales sales = product.getMainCategory().getSales();
                    ProductDTO.SalesDTO salesDTO = new ProductDTO.SalesDTO();
                    salesDTO.setSalesid(sales.getSalesid());
                    salesDTO.setName(sales.getName());
                    salesDTO.setSalesdesc(sales.getSalesdesc());
                    salesDTO.setDiscount(sales.getDiscount());

                    mainCategory.setSales(salesDTO);
                }

                dto.setMainCategory(mainCategory);
            }

            if (product.getSubCategory() != null) {
                dto.setSubcategoryid(product.getSubCategory().getSubcategoryid()); // 設置副類別 ID
                ProductDTO.SubCategoryDTO subCategory = new ProductDTO.SubCategoryDTO();
                subCategory.setSubcategoryid(product.getSubCategory().getSubcategoryid());
                subCategory.setCategoryname(product.getSubCategory().getCategoryname());
                subCategory.setStatus(product.getSubCategory().getStatus());
                dto.setSubCategory(subCategory);
            }

            if (product.getSupplier() != null) {
                dto.setSupplierid(product.getSupplier().getSupplierid()); // 設置廠商 ID
                ProductDTO.SupplierDTO supplierDTO = new ProductDTO.SupplierDTO();
                supplierDTO.setSupplierid(product.getSupplier().getSupplierid());
                supplierDTO.setName(product.getSupplier().getName());
                supplierDTO.setAddress(product.getSupplier().getAddress());
                supplierDTO.setPhone(product.getSupplier().getPhone());
                supplierDTO.setContact(product.getSupplier().getContact());
                supplierDTO.setStatus(product.getSupplier().getStatus());
                dto.setSuppliers(supplierDTO);
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
                List<ProductImage> images = productImageRepository.findAllByProduct_ProductidAndProductColor_Colorsid(
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

    public List<MainCategory> getAllMainCategories() {
        return mainCategoryRepository.findAll();
    }

    public List<SubCategory> getSubCategoriesByMainCategoryId(Long mainCategoryId) {
        return subCategoryRepository.findByMainCategory_Maincategoryid(mainCategoryId);
    }

    //廠商名稱查詢
    public List<Suppliers> getAllSuppliers() {
        return suppliersRepository.findAll();
    }

    public Product updateProduct(Integer productid, ProductDTO productDTO) {

//        System.out.println("productid  "+productid);

        // 查找現有的 Product
        Product existingProduct = productRepository.findById(productid)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productid));


        // 更新基本信息
        existingProduct.setProductname(productDTO.getProductname());
        existingProduct.setProductdescription(productDTO.getProductdescription());
        existingProduct.setWidth(productDTO.getWidth());
        existingProduct.setHeight(productDTO.getHeight());
        existingProduct.setDepth(productDTO.getDepth());
        existingProduct.setUnitprice(productDTO.getUnitprice());
        existingProduct.setDiscountprice(productDTO.getDiscountprice());
        existingProduct.setProductcost(productDTO.getProductcost());
        existingProduct.setStatus(productDTO.getStatus());
        existingProduct.setUnitsold(productDTO.getUnitsold());
        existingProduct.setRating(productDTO.getRating());
        existingProduct.setReviewcount(productDTO.getReviewcount());
        existingProduct.setUpdateat(productDTO.getUpdateat());

        // 更新 MainCategory
        if (productDTO.getMaincategoryid() != null) {
            MainCategory mainCategory = mainCategoryRepository.findById(productDTO.getMaincategoryid())
                    .orElseThrow(() -> new RuntimeException("MainCategory not found with id: " + productDTO.getMaincategoryid()));
            existingProduct.setMainCategory(mainCategory);
        }

        // 更新 SubCategory
        if (productDTO.getSubcategoryid() != null) {
            SubCategory subCategory = subCategoryRepository.findById(productDTO.getSubcategoryid())
                    .orElseThrow(() -> new RuntimeException("SubCategory not found with id: " + productDTO.getSubcategoryid()));
            existingProduct.setSubCategory(subCategory);
        }

        // 更新 Supplier
        if (productDTO.getSupplierid() != null) {
            Suppliers supplier = suppliersRepository.findById(productDTO.getSupplierid())
                    .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + productDTO.getSupplierid()));
            existingProduct.setSupplier(supplier);
        }

        // 保存更新的 Product
        existingProduct = productRepository.save(existingProduct);

//        System.out.println("existingProduct  "+existingProduct.getProductid());

        // 處理商品顏色
        if (productDTO.getProductColors() != null) {

//            System.out.println("ProductDTO"+productDTO.getProductColors());

            for (ProductDTO.ProductColorDTO colorDTO : productDTO.getProductColors()) {
                ProductColor color;

                // 檢查是否為更新或新增
                if (colorDTO.getColorsid() != null) {
                    // 更新顏色
                    color = productColorRepository.findById(colorDTO.getColorsid())
                            .orElseThrow(() -> new RuntimeException("Color not found with id: " + colorDTO.getColorsid()));
//                    System.out.println("UpdateColor "+colorDTO.getColorsid());
                } else {
                    // 新增顏色
                    color = new ProductColor();
                    color.setProduct(existingProduct); // 關聯到產品

                }


                // 更新或設置顏色屬性
                color.setColorname(colorDTO.getColorname());
                color.setStock(colorDTO.getStock());
                color.setMinstock(colorDTO.getMinstock());
                color.setUpdateat(colorDTO.getUpdateat());
                ProductColor tmp = productColorRepository.save(color);// 保存顏色
//                System.out.println("ADDCOLOR COLOR"+ tmp.getColorsid());
//                System.out.println("AddColor"+colorDTO.getColorsid());
//                System.out.println("AddColor"+ colorDTO.getColorname());


                // 處理顏色的圖片
                if (colorDTO.getProductImages() != null) {
                    for (ProductDTO.ProductImageDTO imageDTO : colorDTO.getProductImages()) {
                        ProductImage image;

                        // 檢查是否為更新或新增
                        if (imageDTO.getImageid() != null) {
                            // 更新圖片
                            image = productImageRepository.findById(imageDTO.getImageid())
                                    .orElseThrow(() -> new RuntimeException("Image not found with id: " + imageDTO.getImageid()));
//                            System.out.println("更新"+imageDTO.getImageid());
                        } else {
                            // 新增圖片
                            image = new ProductImage();
                            image.setProduct(existingProduct); // 關聯到產品
                            image.setProductColor(color);      // 關聯到顏色
                        }

                        // 更新或設置圖片屬性
                        image.setImageurl(imageDTO.getImageurl());
                        image.setIsprimary(imageDTO.getIsprimary());
                        image.setUpdatedat(imageDTO.getUpdatedat());
                        productImageRepository.save(image); // 保存圖片
//                        System.out.println("新增"+imageDTO.getImageid());


//                        System.out.println("PRIMARay " + imageDTO.getIsprimary());
                    }
                }
            }
        }

        return existingProduct;
    }

    public void deleteProductById(Integer productid) {
        // 1. 先確認商品是否存在
        Product existingProduct = productRepository.findById(productid)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productid));

        // 2. 先刪所有關聯的圖
        List<ProductImage> images = productImageRepository.findAllByProduct_Productid(productid);
        if (!images.isEmpty()) {
            productImageRepository.deleteAll(images);
//            System.out.println("刪除圖片數量: " + images.size());
        }

        // 3. 再刪所有關聯的顏色
        List<ProductColor> colors = productColorRepository.findByProduct_Productid(productid);
        if (!colors.isEmpty()) {
            productColorRepository.deleteAll(colors);
//            System.out.println("刪除顏色數量: " + colors.size());
        }

        // 4. 最後刪除商品本身
        productRepository.deleteById(productid);
//        System.out.println("刪除商品 ID: " + productid);
    }

    public void deleteProductColorById(Integer colorId) {
        // 1. 先確認顏色是否存在
        ProductColor existingColor = productColorRepository.findById(colorId)
                .orElseThrow(() -> new RuntimeException("ProductColor not found with id: " + colorId));

        // 2. 先刪除該顏色的所有關聯圖片
        List<ProductImage> images = productImageRepository.findAllByProductColor_Colorsid(colorId);
        if (!images.isEmpty()) {
            productImageRepository.deleteAll(images);
//            System.out.println("刪除圖片數量: " + images.size());
        }

        // 3. 最後刪除顏色
        productColorRepository.deleteById(colorId);
//        System.out.println("刪除顏色 ID: " + colorId);
    }

    public List<ProductDTO> searchProducts(String productname, Integer maincategoryid, Integer subcategoryid) {
        List<Product> products = productRepository.searchProducts(
                productname != null && !productname.isEmpty() ? productname : null,
                maincategoryid != null && maincategoryid > 0 ? maincategoryid : null,
                subcategoryid != null && subcategoryid > 0 ? subcategoryid : null
        );

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

            if (product.getMainCategory() != null) {
                ProductDTO.MainCategoryDTO mainCategoryDTO = new ProductDTO.MainCategoryDTO();
                mainCategoryDTO.setMaincategoryid(product.getMainCategory().getMaincategoryid());
                mainCategoryDTO.setCategoryname(product.getMainCategory().getCategoryname());
                mainCategoryDTO.setStatus(product.getMainCategory().getStatus());

                if (product.getMainCategory().getSales() != null) {
                    ProductDTO.SalesDTO salesDTO = new ProductDTO.SalesDTO();
                    salesDTO.setSalesid(product.getMainCategory().getSales().getSalesid());
                    salesDTO.setName(product.getMainCategory().getSales().getName());
                    salesDTO.setSalesdesc(product.getMainCategory().getSales().getSalesdesc());
                    salesDTO.setDiscount(product.getMainCategory().getSales().getDiscount());
                    mainCategoryDTO.setSales(salesDTO);
                }

                dto.setMainCategory(mainCategoryDTO);
            }

            if (product.getSubCategory() != null) {
                ProductDTO.SubCategoryDTO subCategoryDTO = new ProductDTO.SubCategoryDTO();
                subCategoryDTO.setSubcategoryid(product.getSubCategory().getSubcategoryid());
                subCategoryDTO.setCategoryname(product.getSubCategory().getCategoryname());
                subCategoryDTO.setStatus(product.getSubCategory().getStatus());
                dto.setSubCategory(subCategoryDTO);
            }

            if (product.getSupplier() != null) {
                ProductDTO.SupplierDTO supplierDTO = new ProductDTO.SupplierDTO();
                supplierDTO.setSupplierid(product.getSupplier().getSupplierid());
                supplierDTO.setName(product.getSupplier().getName());
                supplierDTO.setAddress(product.getSupplier().getAddress());
                supplierDTO.setPhone(product.getSupplier().getPhone());
                supplierDTO.setContact(product.getSupplier().getContact());
                supplierDTO.setStatus(product.getSupplier().getStatus());
                dto.setSuppliers(supplierDTO);
            }

            List<ProductColor> colors = productColorRepository.findByProduct_Productid(product.getProductid());
            List<ProductDTO.ProductColorDTO> colorDTOs = colors.stream().map(color -> {
                ProductDTO.ProductColorDTO colorDTO = new ProductDTO.ProductColorDTO();
                colorDTO.setColorsid(color.getColorsid());
                colorDTO.setColorname(color.getColorname());
                colorDTO.setStock(color.getStock());
                colorDTO.setMinstock(color.getMinstock());
                colorDTO.setUpdateat(color.getUpdateat());

                // 查詢顏色相關的圖片
                List<ProductImage> images = productImageRepository.findAllByProduct_ProductidAndProductColor_Colorsid(
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

    public ProductDTO getProductById(Integer productid) {
        Product product = productRepository.findById(productid)
                .orElseThrow(() -> new RuntimeException("找不到商品 ID: " + productid));

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

        if (product.getMainCategory() != null) {
            dto.setMaincategoryid(product.getMainCategory().getMaincategoryid()); // 設置主類別 ID

            ProductDTO.MainCategoryDTO mainCategory = new ProductDTO.MainCategoryDTO();
            mainCategory.setMaincategoryid(product.getMainCategory().getMaincategoryid());
            mainCategory.setCategoryname(product.getMainCategory().getCategoryname());
            mainCategory.setStatus(product.getMainCategory().getStatus());

            // **處理 Sales (活動)**
            if (product.getMainCategory().getSales() != null) {
                Sales sales = product.getMainCategory().getSales();
                ProductDTO.SalesDTO salesDTO = new ProductDTO.SalesDTO();
                salesDTO.setSalesid(sales.getSalesid());
                salesDTO.setName(sales.getName());
                salesDTO.setSalesdesc(sales.getSalesdesc());
                salesDTO.setDiscount(sales.getDiscount());

                mainCategory.setSales(salesDTO);
            }

            dto.setMainCategory(mainCategory);
        }

        if (product.getSubCategory() != null) {
            dto.setSubcategoryid(product.getSubCategory().getSubcategoryid()); // 設置副類別 ID
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
            List<ProductImage> images = productImageRepository.findAllByProduct_ProductidAndProductColor_Colorsid(
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
    }

    public List<ProductDTO> getProductsByMainCategory(Integer maincategoryid) {
        List<Product> products = productRepository.findByMainCategory(maincategoryid);

        return products.stream().map(product -> {
            ProductDTO dto = new ProductDTO();
            dto.setProductid(product.getProductid());
            dto.setProductname(product.getProductname());
            dto.setProductdescription(product.getProductdescription());
            dto.setMaincategoryid(product.getMainCategory().getMaincategoryid());
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

            // 設置主類別
            if (product.getMainCategory() != null) {
                ProductDTO.MainCategoryDTO mainCategoryDTO = new ProductDTO.MainCategoryDTO();
                mainCategoryDTO.setMaincategoryid(product.getMainCategory().getMaincategoryid());
                mainCategoryDTO.setCategoryname(product.getMainCategory().getCategoryname());
                mainCategoryDTO.setStatus(product.getMainCategory().getStatus());
                if (product.getMainCategory().getSales() != null) {
                    ProductDTO.SalesDTO salesDTO = new ProductDTO.SalesDTO();
                    salesDTO.setSalesid(product.getMainCategory().getSales().getSalesid());
                    salesDTO.setName(product.getMainCategory().getSales().getName());
                    salesDTO.setSalesdesc(product.getMainCategory().getSales().getSalesdesc());
                    salesDTO.setDiscount(product.getMainCategory().getSales().getDiscount());
                    mainCategoryDTO.setSales(salesDTO);
                }
                dto.setMainCategory(mainCategoryDTO);
            }

            // 設置副類別
            if (product.getSubCategory() != null) {
                ProductDTO.SubCategoryDTO subCategoryDTO = new ProductDTO.SubCategoryDTO();
                subCategoryDTO.setSubcategoryid(product.getSubCategory().getSubcategoryid());
                subCategoryDTO.setCategoryname(product.getSubCategory().getCategoryname());
                subCategoryDTO.setStatus(product.getSubCategory().getStatus());
                dto.setSubCategory(subCategoryDTO);
            }

            // 設置 ProductColors 和 ProductImages
            List<ProductColor> colors = productColorRepository.findByProduct_Productid(product.getProductid());
            List<ProductDTO.ProductColorDTO> colorDTOs = colors.stream().map(color -> {
                ProductDTO.ProductColorDTO colorDTO = new ProductDTO.ProductColorDTO();
                colorDTO.setColorsid(color.getColorsid());
                colorDTO.setColorname(color.getColorname());
                colorDTO.setStock(color.getStock());
                colorDTO.setMinstock(color.getMinstock());
                colorDTO.setUpdateat(color.getUpdateat());

                // 設置 ProductImages
                List<ProductImage> images = productImageRepository.findAllByProduct_ProductidAndProductColor_Colorsid(
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

    public List<ProductReviewDTO> getProductReviews(Integer productid) {
        List<Review> reviews = reviewRepository.findByProduct_Productid(productid);

        return reviews.stream().map(review -> {
            ProductReviewDTO dto = new ProductReviewDTO();
            dto.setReviewid(review.getReviewid());
            dto.setProductid(review.getProduct().getProductid());

            if (review.getMember() != null) {
                dto.setRealname(review.getMember().getRealname());
            } else {
                dto.setRealname("匿名用戶");
            }

            dto.setRating(review.getRating());
            dto.setContent(review.getContent());


            return dto;
        }).collect(Collectors.toList());
    }
}