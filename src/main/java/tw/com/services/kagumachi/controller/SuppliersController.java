package tw.com.services.kagumachi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.services.kagumachi.model.Suppliers;
import tw.com.services.kagumachi.service.SuppliersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/suppliers")
public class SuppliersController {

	@Autowired
	private SuppliersService suppliersService;
	
	@GetMapping("/allsuppliers")
	private ResponseEntity<?> getallsuppliers() {
		return suppliersService.getAllSuppliers();
	}
	
	@GetMapping("/getallnames")
	public List<String> getAllNames() {
		return suppliersService.getAllSuppliersNames(); 
	}
		
	@PostMapping("/getbysearch")
	public ResponseEntity<?> searchsuppliers(@RequestBody Map<String, String> payload) {
	    String supplierName = payload.get("supplierName");
	    String subcategoryName = payload.get("subcategoryName");

	    List<Suppliers> suppliers = suppliersService.searchSuppliers(supplierName, subcategoryName);
	    
	    if (suppliers.isEmpty()) {
	        Map<String, String> errorResponse = new HashMap<>();
	        errorResponse.put("message", "未找到符合條件的廠商");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	    }
	    
	    return ResponseEntity.ok(suppliers);
	}
	
	@PostMapping("/addsupplier")
	public ResponseEntity<?> addsupplier (@RequestBody Map<String, String> payload) {
		String addSupplierName = payload.get("supplierName");
		String addSubcategoryName = payload.get("subcategoryName");
		String addSupplierAddress= payload.get("supplierAddress");
		String addSupplierPhone= payload.get("supplierPhone");
		String addContactor= payload.get("contactor");
		suppliersService.addSupplier(addSupplierName, addSubcategoryName, addSupplierAddress, addSupplierPhone, addContactor);
		Map<String, String> response = new HashMap<>();
	    response.put("message", "新增成功");
	    return ResponseEntity.ok(response);
	}
	
	@PostMapping("/updatesupplier")
	public ResponseEntity<?> updatesupplier (@RequestBody Map<String, Object> payload) {
		Integer updateSupplierId = (Integer)payload.get("supplierid");
		String updateSupplierName = (String)payload.get("name");
		String updateSupplierAddress = (String)payload.get("address");
		String updateSupplierPhone = (String)payload.get("phonenumber");
		String updateContactor = (String)payload.get("contactor");
		suppliersService.updateSupplier(updateSupplierId, updateSupplierName, updateSupplierAddress, updateSupplierPhone, updateContactor);
		Map<String, String> response = new HashMap<>();
	    response.put("message", "更新成功");
	    return ResponseEntity.ok(response);
	}
 	
	@PostMapping("/deletesupplier")
	public ResponseEntity<?> deletesupplier (@RequestBody Map<String, Object> payload) {
		Integer updateSupplierId = (Integer)payload.get("supplierid");
		
		if (updateSupplierId == null) {
	        throw new IllegalArgumentException("Supplier ID must not be null");
	    }
		suppliersService.deleteSupplier(updateSupplierId);
		Map<String, String> response = new HashMap<>();
	    response.put("message", "刪除成功");
	    return ResponseEntity.ok(response);
	}
	
	
	
}
