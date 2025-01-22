package tw.com.services.kagumachi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.services.kagumachi.service.SubCategoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/subcategories")
public class SubCategoryController {

	@Autowired
	private SubCategoryService subCategoryService;
	
	@GetMapping("/getallnames")
	public List<String> getSubcategoryNames() {
		return subCategoryService.getSubcategoryNames();
	}
	
}
