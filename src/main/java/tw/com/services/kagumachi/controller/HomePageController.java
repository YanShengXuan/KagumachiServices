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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.com.services.kagumachi.model.MyKeep;
import tw.com.services.kagumachi.model.Product;
import tw.com.services.kagumachi.model.ProductColor;
import tw.com.services.kagumachi.model.ProductImage;
import tw.com.services.kagumachi.repository.MyKeepRepository;
import tw.com.services.kagumachi.repository.ProductColorRepository;
import tw.com.services.kagumachi.repository.ProductImageRepository;
import tw.com.services.kagumachi.repository.ProductRepository;
import tw.com.services.kagumachi.service.ProductService;

@RestController
@RequestMapping("/myhome")
public class HomePageController {
	
	@Autowired
    private MyKeepRepository myKeepRepository;
	
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
			jsonobject.put("unitprice", product.getUnitprice());

			jsonarray.put(jsonobject);
		}
		return jsonarray.toString();

	}
	@GetMapping("/testm")
    public String getAllMyKeeps(@RequestParam Integer memberid) {
		List<MyKeep> myKeepsList = myKeepRepository.findByMember_Memberid(memberid);
		JSONArray jsonArray = new JSONArray();
		for (MyKeep myKeep : myKeepsList) {
            int productid = myKeep.getProduct().getProductid();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("productid", productid);
            jsonArray.put(jsonObject);
		}
		return jsonArray.toString();
    }
}
