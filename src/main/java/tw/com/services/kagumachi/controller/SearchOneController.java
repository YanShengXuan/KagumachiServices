package tw.com.services.kagumachi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
@RequestMapping("/mysearch")
public class SearchOneController {

	@Autowired
    private ProductRepository productrepository;
	
	@Autowired
	private ProductImageRepository productImageRepository;
	
	@Autowired
	private ProductColorRepository productColorRepository;
	
	@GetMapping("/sone/{se}")
	public String getProducts(@PathVariable String se) {
		List<Product> list = productrepository.findByProductnameContaining(se);
		System.out.println(list.size());
		for (Product product : list) {
			System.out.printf("%s\n", product.getProductname());
		}

		JSONArray jsonarray = new JSONArray();
		
		for(Product product : list) {
			JSONObject jsonobject = new JSONObject();
			jsonobject.put("count", list.size());
			jsonobject.put("productid",product.getProductid());
			jsonobject.put("dataname", product.getProductname());
			
			int productid = product.getProductid();
			List<ProductColor> productcolors = productColorRepository.findByProduct_Productid(productid);
            JSONArray productdetails = new JSONArray();
            for (ProductColor productcolor : productcolors) {
                JSONObject productdetail = new JSONObject();
//                productdetail.put("color", productcolor.getColorname());
                int colorsid = productcolor.getColorsid();
                List<ProductImage> productImage = productImageRepository.findAllByProduct_ProductidAndProductColor_Colorsid(productid, colorsid);
                String imageUrl = productImage.stream()
                        .findFirst()
                        .map(ProductImage::getImageurl)
                        .orElse(""); // 如果沒有圖片，返回空字串

                productdetail.put("dataimage", imageUrl);
                productdetails.put(productdetail);
            }
            
            jsonobject.put("productdetails", productdetails);	
            jsonobject.put("discountprice", product.getDiscountprice());
			jsonobject.put("unitprice", product.getUnitprice());

			jsonarray.put(jsonobject);
			}
		return jsonarray.toString();
	}
	
	@GetMapping("/sone/{se}/{pri}")
	public String getnewProducts(@PathVariable String se,@PathVariable Integer pri) {
		List<Product> list = productrepository.findByProductnameContaining(se);
//		System.out.println(list.size());
		JSONArray jsonarray = new JSONArray();
		int count=0;
		for (Product product : list) {
			if(product.getDiscountprice()< pri) {
				System.out.println(product.getProductname());
				System.out.println(product.getDiscountprice());
				count++;
			}
		}
		for(Product product : list) {
			if(product.getDiscountprice()<= pri) {
				JSONObject jsonobject = new JSONObject();
				jsonobject.put("count", count);
				jsonobject.put("productid",product.getProductid());
				jsonobject.put("dataname", product.getProductname());
				
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
	            
	            jsonobject.put("productdetails", productdetails);	
	            jsonobject.put("discountprice", product.getDiscountprice());
				jsonobject.put("unitprice", product.getUnitprice());
				
				jsonarray.put(jsonobject);	
				}
			}
		if(jsonarray.length()==0) {		 		
	 		return jsonarray.toString();
	 	}

		return jsonarray.toString();

	}
}