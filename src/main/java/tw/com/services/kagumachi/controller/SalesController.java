package tw.com.services.kagumachi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.services.kagumachi.model.MainCategory;
import tw.com.services.kagumachi.model.Sales;
import tw.com.services.kagumachi.service.SalesService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;





@RestController
@RequestMapping("/sales")
public class SalesController {
	
	@Autowired
	private SalesService salesService;

	@GetMapping("/getallsales")
	public List<Sales> getallsales() {
		return salesService.getallsales(); 
	}
	
	@PostMapping("/addsale")
	public ResponseEntity<?> addsupplier (@RequestBody Map<String, Object> payload) {
		String addSaleName = (String)payload.get("saleName");
		String addSaleDescription = (String)payload.get("saleDescription");
		
		Double addDiscount = null;
		try {
			addDiscount = Double.parseDouble((String) payload.get("discount"));
		}catch(Exception e){
			System.out.println(e);
		};
		
		salesService.addSale(addSaleName, addSaleDescription, addDiscount);
		Map<String, Object> response = new HashMap<>();
	    response.put("message", "新增成功");
	    return ResponseEntity.ok(response);
	}
	
	@PostMapping("/updatesale")
	public ResponseEntity<?> updatesupplier (@RequestBody Map<String, Object> payload) {
		Integer updateSaleId = (Integer)payload.get("saleid");
		String updateSaleName = (String)payload.get("salename");
		String updateSaleDescription = (String)payload.get("saledesc");
		
//		Double updateDiscount = null;
//		try {
//			updateDiscount = Double.parseDouble((String) payload.get("discount"));
//		}catch(Exception e){
//			System.out.println(e);
//		};
		
		salesService.updateSale(updateSaleId, updateSaleName, updateSaleDescription);
		Map<String, Object> response = new HashMap<>();
	    response.put("message", "新增成功");
	    return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/deletesale")
	public ResponseEntity<?> deletesale (@RequestBody Map<String, Object> payload) {
		Integer updateSaleId = (Integer)payload.get("saleid");
		if (updateSaleId == null) {
	        throw new IllegalArgumentException("Sale ID must not be null");
	    }
//		salesService.deleteSale(updateSaleId);
//		Map<String, String> response = new HashMap<>();
//	    response.put("message", "刪除成功");
//	    return ResponseEntity.ok(response);
		try {
	        salesService.deleteSale(updateSaleId);
	        return ResponseEntity.ok(Map.of("message", "刪除成功"));
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", "目前該活動被使用中，無法直接刪除"));
	    }
		
	}
	
	@GetMapping("/getmaincategories")
	public List<MainCategory>  getmaincategories() {
		return salesService.getmaincategories();
	}
	
	@PostMapping("/updatecategoryanddiscount")
	public ResponseEntity<?> updatecategory (@RequestBody Map<String, String> payload) {
		Integer updateCategoryId = Integer.parseInt(payload.get("categoryid"));
	    Integer updateSaleId = Integer.parseInt(payload.get("salesid"));
		
		salesService.updateCategoryAndDiscount(updateCategoryId, updateSaleId);
		Map<String, String> response = new HashMap<>();
	    response.put("message", "修改成功");
	    return ResponseEntity.ok(response);
	}
	
	
}
