package tw.com.services.kagumachi.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.com.services.kagumachi.model.Product;
import tw.com.services.kagumachi.model.ProductColor;
import tw.com.services.kagumachi.model.ProductImage;
import tw.com.services.kagumachi.repository.ProductColorRepository;
import tw.com.services.kagumachi.repository.ProductImageRepository;
import tw.com.services.kagumachi.repository.ProductRepository;

@RestController
@RequestMapping("/mysearchtwo")
public class SearchTwoController {
	
	@Autowired
    private ProductRepository productRepository;

	@Autowired
	private ProductImageRepository productImageRepository;
	
	@Autowired
	private ProductColorRepository productColorRepository;
    
	@GetMapping("/test")
	public List<Product> getProductsByCategory(@RequestParam int maincategoryid, @RequestParam int subcategoryid) {
        return productRepository.findByCategory(maincategoryid, subcategoryid);
    }
	
	@GetMapping("/stwo/{main}/{sub}")
	public String getProducts(@PathVariable int main,@PathVariable int sub) {
		List<Product> list = productRepository.findByCategory(main, sub);
		JSONArray jsonarray = new JSONArray();
		 for (Product product : list) {
	            JSONObject jsonObject = new JSONObject();
	            jsonObject.put("count", list.size());
	            jsonObject.put("productid", product.getProductid()); 
	            jsonObject.put("dataname", product.getProductname()); 
	            jsonObject.put("discountprice", product.getDiscountprice());
				jsonObject.put("unitprice", product.getUnitprice());
	            
	            int productid = product.getProductid();
				List<ProductColor> productcolors = productColorRepository.findByProduct_Productid(productid);
	            JSONArray productdetails = new JSONArray();
	            for (ProductColor productcolor : productcolors) {
	                JSONObject productdetail = new JSONObject();
//	                productdetail.put("color", productcolor.getColorname());
	                int colorsid = productcolor.getColorsid();
	                List<ProductImage> productImage = productImageRepository.findAllByProduct_ProductidAndProductColor_Colorsid(productid, colorsid);
	                String imageUrl = productImage.stream()
	                        .findFirst()
	                        .map(ProductImage::getImageurl)
	                        .orElse(""); // 如果沒有圖片，返回空字串

	                productdetail.put("dataimage", imageUrl);
	                productdetails.put(productdetail);
	            }
	            
	            jsonObject.put("productdetails", productdetails);	
	            jsonarray.put(jsonObject); 
	        }

	        return jsonarray.toString();
		
	}
	@GetMapping("/stwo/{main}/{sub}/{pri}")
	public String getProducts(@PathVariable int main,@PathVariable int sub,@PathVariable Integer pri) {
		List<Product> list = productRepository.findByCategory(main, sub);
		JSONArray jsonarray = new JSONArray();
		int count=0;
		for (Product product : list) {
			if(product.getDiscountprice()< pri) {
				System.out.println(product.getProductname());
				System.out.println(product.getDiscountprice());
				count++;
			}
		}
		 for (Product product : list) {
			 if(product.getDiscountprice()<= pri) {
	            JSONObject jsonObject = new JSONObject();
	            jsonObject.put("count", list.size());
	            jsonObject.put("productid", product.getProductid()); 
	            jsonObject.put("dataname", product.getProductname()); 
	            jsonObject.put("discountprice", product.getDiscountprice());
				jsonObject.put("unitprice", product.getUnitprice());
	            
	            int productid = product.getProductid();
				List<ProductColor> productcolors = productColorRepository.findByProduct_Productid(productid);
	            JSONArray productdetails = new JSONArray();
	            for (ProductColor productcolor : productcolors) {
	                JSONObject productdetail = new JSONObject();
//	                productdetail.put("color", productcolor.getColorname());
	                int colorsid = productcolor.getColorsid();
	                List<ProductImage> productImage = productImageRepository.findAllByProduct_ProductidAndProductColor_Colorsid(productid, colorsid);
	                String imageUrl = productImage.stream()
	                        .findFirst()
	                        .map(ProductImage::getImageurl)
	                        .orElse(""); // 如果沒有圖片，返回空字串

	                productdetail.put("dataimage", imageUrl);
	                productdetails.put(productdetail);
	            }
	            
	            jsonObject.put("productdetails", productdetails);	
	            jsonarray.put(jsonObject); 
	        }
		 }
	        return jsonarray.toString();
		
	}
}
