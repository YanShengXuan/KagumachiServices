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
	}
	
	@GetMapping("/sone/{se}/{pri}")
	public String getnewProducts(@PathVariable String se,@PathVariable Integer pri) {
		List<Product> list = productrepository.findByProductnameContaining(se);
//		System.out.println(list.size());
		int count=0;
		for (Product product : list) {
			if(product.getDiscountprice()< pri) {
				System.out.println(product.getProductname());
				System.out.println(product.getDiscountprice());
				count++;
			}
		}
		JSONArray jsonarray = new JSONArray();
		
		for(Product product : list) {
			if(product.getDiscountprice()<= pri) {
				JSONObject jsonobject = new JSONObject();
				jsonobject.put("count", count);
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
			}
		return jsonarray.toString();

	}
}