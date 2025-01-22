package tw.com.services.kagumachi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import tw.com.services.kagumachi.model.MainCategory;
import tw.com.services.kagumachi.model.Sales;
import tw.com.services.kagumachi.repository.MainCategoryRepository;
import tw.com.services.kagumachi.repository.SalesRepository;

@Service
public class SalesService {

	@Autowired
	private SalesRepository salesRepository;
	
	@Autowired
	private MainCategoryRepository mainCategoryRepository;
	
	public List<Sales> getallsales() {
		return salesRepository.findAll();
	}
	
	public void addSale(String addSaleName, String addSaleDescription, Double addDiscount) {
		Sales newSale = new Sales();
		newSale.setName(addSaleName);
		newSale.setSalesdesc(addSaleDescription);
		newSale.setDiscount(addDiscount);
		salesRepository.save(newSale);
	}
	
	public void updateSale(Integer updateSaleId, String updateSaleName, String updateSaleDescription, Double updateDiscount) {
		Sales updatedSale = salesRepository.findById(updateSaleId)
				.orElseThrow(() -> new EntityNotFoundException("Sale not found with id: " + updateSaleId));
		updatedSale.setName(updateSaleName);
		updatedSale.setSalesdesc(updateSaleDescription);
		updatedSale.setDiscount(updateDiscount);
		salesRepository.save(updatedSale);
	}
	
	public void deleteSale(Integer updateSaleId) {
		Sales deletedSale = salesRepository.findById(updateSaleId)
				.orElseThrow(() -> new EntityNotFoundException("Sale not found with id: " + updateSaleId));
		salesRepository.delete(deletedSale);
	}
	
	public List<MainCategory> getmaincategories() {
		return mainCategoryRepository.findAll();
	}
	
	public void getupatecategory(Integer categoryId, Integer salesId) {
		MainCategory updatedMainCategory = mainCategoryRepository.findById(categoryId)
	            .orElseThrow(() -> new RuntimeException("Category not found"));
		
		Sales updatedsales = salesRepository.findById(salesId)
	            .orElseThrow(() -> new RuntimeException("Sales not found"));
		
		updatedMainCategory.setSales(updatedsales);
		mainCategoryRepository.save(updatedMainCategory);
	}
}
