package tw.com.services.kagumachi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.services.kagumachi.model.SubCategory;
import tw.com.services.kagumachi.repository.SubCategoryRepository;

@Service
public class SubCategoryService {
	
	@Autowired
	private SubCategoryRepository subCategoryRepository;
	
	public List<String> getSubcategoryNames	(){
		return subCategoryRepository.findAllCategoryNames();
	}
	
	public List<SubCategory> getSubcategories() {
		return subCategoryRepository.findAll();
	}
}
