package tw.com.services.kagumachi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import tw.com.services.kagumachi.model.Product;
import tw.com.services.kagumachi.model.SubCategory;
import tw.com.services.kagumachi.model.Suppliers;
import tw.com.services.kagumachi.repository.ProductRepository;
import tw.com.services.kagumachi.repository.SubCategoryRepository;
import tw.com.services.kagumachi.repository.SuppliersRepository;

@Service
public class SuppliersService {
	
	@Autowired
	private SuppliersRepository suppliersRepository;
	
	@Autowired
    private SubCategoryRepository subCategoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	public ResponseEntity<?> getAllSuppliers() {
		List<Suppliers> suppliers = suppliersRepository.findAll();
		for (Suppliers supplier : suppliers) {
	    	if (supplier.getStatus() == null) {
	    		supplier.setStatus("未合作");
	    	}
	    }
		return ResponseEntity.ok(suppliers);
	}
	
	public List<String> getAllSuppliersNames() {
		return suppliersRepository.findAllSupplierNames();
	}
	
	public List<Suppliers> searchSuppliers(String supplierName, String categoryName) {
		List<Suppliers> suppliers;
		
		if (supplierName == null && categoryName == null) {
			suppliers = suppliersRepository.findAll();
		}
		
		else if (supplierName != null && categoryName == null) {
			suppliers = suppliersRepository.findByName(supplierName);
		}
		
		else if (supplierName == null && categoryName != null) {
	        SubCategory subCategory = subCategoryRepository.findByCategoryname(categoryName);
	        suppliers = suppliersRepository.findBySubCategory(subCategory);
	    }
		
		else {
	        SubCategory subCategory = subCategoryRepository.findByCategoryname(categoryName);
	        suppliers = suppliersRepository.findByNameAndSubCategory(supplierName, subCategory);
	    }
		
		for (Suppliers supplier : suppliers) {
	        if (supplier.getStatus() == null) {
	            supplier.setStatus("未合作");
	        }
	    }
	
        return suppliers;
    }
	
	public void addSupplier(String addSupplierName, String addSubcategoryName, String addSupplierAddress, String addSupplierPhone, String addContactor) {
		SubCategory addSubCategory = subCategoryRepository.findByCategoryname(addSubcategoryName);
        Suppliers newSupplier = new Suppliers();
        newSupplier.setName(addSupplierName);
        newSupplier.setSubCategory(addSubCategory);
        newSupplier.setAddress(addSupplierAddress);
        newSupplier.setPhone(addSupplierPhone);
        newSupplier.setContact(addContactor);

        suppliersRepository.save(newSupplier);
	}
	
	public void updateSupplier(Integer updateSupplierId, String updateSupplierName, String updateSupplierAddress, String updateSupplierPhone, String updateContactor) {
		Suppliers updatedSupplier = suppliersRepository.findById(updateSupplierId)
				.orElseThrow(() -> new EntityNotFoundException("Supplier not found with id: " + updateSupplierId));
		updatedSupplier.setName(updateSupplierName);
		updatedSupplier.setAddress(updateSupplierAddress);
		updatedSupplier.setPhone(updateSupplierPhone);
		updatedSupplier.setContact(updateContactor);
		suppliersRepository.save(updatedSupplier);
	}
	
	public void deleteSupplier(Integer updateSupplierId) {
		Suppliers deletedSupplier = suppliersRepository.findById(updateSupplierId)
				.orElseThrow(() -> new EntityNotFoundException("Supplier not found with id: " + updateSupplierId));
		suppliersRepository.delete(deletedSupplier);
	}
	
	// 定期更新廠商合作狀態
//	@Scheduled(cron = "0 0 * * * ?")
//	public void syncSupplierStatus() {
//	    List<Suppliers> suppliersList = suppliersRepository.findAll();
//	    for (Suppliers supplier : suppliersList) {
//	        List<Product> products = productRepository.findBySupplierId(supplier.getSupplierid());
//	        
//	        boolean hasActiveProduct = false;
//	        
//	        for (Product product : products) {
//	            if ("上架中".equals(product.getStatus())) {
//	                hasActiveProduct = true;
//	                break;
//	            }
//	        }
//	        
//	        // 更新廠商狀態
//	        if (hasActiveProduct) {
//	            supplier.setStatus("合作中");
//	        } else {
//	            supplier.setStatus("未合作");
//	        }
//	        
//	        suppliersRepository.save(supplier);
//	    }
//	}
}
