package tw.com.services.kagumachi.controller;


import com.sun.tools.javac.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.services.kagumachi.model.MainCategory;
import tw.com.services.kagumachi.model.SubCategory;
import tw.com.services.kagumachi.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/Category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // 取得所有主分類
    @GetMapping("/getMain")
    public List<MainCategory> getAllMainCategories() {
        return categoryService.getAllMainCategories();
    }

    // 取得特定主分類下的所有子分類
    @GetMapping("/getSub")
    public List<SubCategory> getSubCategoriesByMainCategoryId(@RequestParam Integer mainCategoryId) {
        return categoryService.getSubCategoriesByMainCategoryId(mainCategoryId);
    }

    //  刪
    @DeleteMapping("/deleteMain/{maincategoryId}")
    public void deleteMainCategory(@PathVariable Integer maincategoryId) {
        categoryService.deleteMainCategory(maincategoryId);
    }

    @DeleteMapping("/deleteSub/{subcategoryId}")
    public void deleteSubCategory(@PathVariable Integer subcategoryId) {
        categoryService.deleteSubCategory(subcategoryId);
    }

    @PutMapping("/updateMain/{id}")
    public MainCategory updateMainCategory(@PathVariable Integer id, @RequestBody MainCategory updateMain){

        return categoryService.updateMainCategory(id, updateMain);
    }

    @PutMapping("/updateSub/{id}")
    public SubCategory updateSubCategory(@PathVariable Integer id, @RequestBody SubCategory updateSub){

        return categoryService.updateSubCategory(id, updateSub);
    }

    @PostMapping("/addMain")
    public void addMainCategory(@RequestBody MainCategory mainCategory) {
        System.out.println("收到請求: " + mainCategory.getCategoryname() + " | 狀態: " + mainCategory.getStatus());
        categoryService.addMainCategory(mainCategory);
    }

    @PostMapping("/addSub")
    public void addSubCategory(@RequestBody SubCategory subCategory) {
        if (subCategory.getMainCategory() == null) {
            throw new IllegalArgumentException("主分類 ID 不能為空");
        }
        categoryService.addSubCategory(subCategory, subCategory.getMainCategory().getMaincategoryid());
    }
}
