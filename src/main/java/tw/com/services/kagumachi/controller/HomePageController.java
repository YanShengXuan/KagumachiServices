package tw.com.services.kagumachi.controller;

<<<<<<< HEAD
=======

>>>>>>> eb2fcfd6af9a9a187f9069324e4ed9e47e23fc85
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.services.kagumachi.model.Product;
import tw.com.services.kagumachi.model.ProductColor;
import tw.com.services.kagumachi.model.ProductImage;
import tw.com.services.kagumachi.repository.ProductColorRepository;
import tw.com.services.kagumachi.repository.ProductImageRepository;
import tw.com.services.kagumachi.repository.ProductRepository;
import tw.com.services.kagumachi.service.ProductService;

@RestController
@RequestMapping("/myhome")
public class HomePageController {
	
	@Autowired
    private ProductRepository productrepository;
	
	@Autowired
	private ProductImageRepository productImageRepository;
	
	@Autowired
	private ProductColorRepository productColorRepository;
	
	@GetMapping("/test")
	public String  getProducts() {
		
		List<Optional<Product>> productlist = new ArrayList<Optional<Product>>();
		
		for(int i=1;i<=6;i++) {
			productlist.add(productrepository.findById(i));
		}
//		for(int i=1;i<=6;i++) {
//			System.out.println(productlist.get(i-1).get().getProductname());
//		}
		
//		List<ProductColor> productcolors = productColorRepository.findByProduct_Productid(productlist.get(1).get().getProductid());
//		for(ProductColor productcolor : productcolors) {
//			System.out.println(productcolor.getColorname());
//		}

		JSONArray jsonarray = new JSONArray();
		for(int i=0;i<productlist.size();i++) {
			Product product = productlist.get(i).get();
			JSONObject jsonobject = new JSONObject();
			jsonobject.put("productid",product.getProductid());
			jsonobject.put("dataname", product.getProductname());
			
			int productid = product.getProductid();
//			List<ProductColor> productcolors = productColorRepository.findByProduct_Productid(productid);		
//			int colorsid = productcolors.get(0).getColorsid();
//			Optional<ProductImage> productImage = productImageRepository.findByProduct_ProductidAndProductColor_Colorsid(productid, colorsid);//與color資料表連接
//			String imgurl = productImage.get().getImageurl();
//			jsonobject.put("dataimage", imgurl);
			
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
//			jsonobject.put("unitprice", product.getUnitprice());

			jsonarray.put(jsonobject);
		}
		return jsonarray.toString();
		
//		int productid = productlist.get(0).get().getProductid();
//		System.out.println(productid);
//		int colorsid = productcolors.get(0).getColorsid();
//		Optional<ProductImage> productImage = productImageRepository.findByProduct_ProductidAndProductColor_Colorsid(productid, colorsid);//與color資料表連接
//		String imgurl = productImage.get().getImageurl();
//		System.out.println(imgurl);	
	
//		for(int i=1;i<=6;i++) {
//			System.out.println(productlist.get(i-1).get().getDiscountprice());
//			System.out.println(productlist.get(i-1).get().getUnitprice());
//		}
		

	}

}
