package tw.com.services.kagumachi.controller;

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
//		System.out.println(productlist.size());//6
		
//		int productid = 2;	
//		
		JSONArray jsonarray = new JSONArray();
		for(int i=0;i<productlist.size();i++) {
			Product product = productlist.get(i).get();
			JSONObject jsonobject = new JSONObject();
			jsonobject.put("dataname", product.getProductname());
			
			int productid = product.getProductid();
			List<ProductColor> productcolors = productColorRepository.findByProduct_Productid(productid);
			int colorsid = productcolors.get(0).getColorsid();
			
			Optional<ProductImage> productImage = productImageRepository.findByProduct_ProductidAndProductColor_Colorsid(productid, colorsid);//與color資料表連接
			String imgurl = productImage.get().getImageurl();
			jsonobject.put("dataimage", imgurl);
			jsonobject.put("dataprice", product.getDiscountprice());
			
			jsonarray.put(jsonobject);
		}
		return jsonarray.toString();
		
//		int productid = productlist.get(0).get().getProductid();
//		System.out.println(productid);
//		int colorsid = productcolors.get(0).getColorsid();
//		Optional<ProductImage> productImage = productImageRepository.findByProduct_ProductidAndProductColor_Colorsid(productid, colorsid);//與color資料表連接
//		String imgurl = productImage.get().getImageurl();
//		System.out.println(imgurl);	
	
		
		
	
		//一個產品多張圖片
//		for (int i = 0; i < productcolors.size(); i++) {
//		    int colorsid = productcolors.get(i).getColorsid();
//		    Optional<ProductImage> productImage = productImageRepository.findByProduct_ProductidAndProductColor_Colorsid(productid, colorsid);
//		    
//		    if (productImage.isPresent()) {
//		        String imgurl = productImage.get().getImageurl();
//		        System.out.println(imgurl);
//		    } else {
//		        System.out.println("Image not found for colorsid: " + colorsid);
//		    }
//		}
		
//		for(int i=1;i<=6;i++) {
//			System.out.println(productlist.get(i-1).get().getDiscountprice());
//			System.out.println(productlist.get(i-1).get().getUnitprice());
//		}
		

	}

}
