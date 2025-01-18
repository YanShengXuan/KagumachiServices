package tw.com.services.kagumachi.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.services.kagumachi.model.MainCategory;
import tw.com.services.kagumachi.model.SubCategory;
import tw.com.services.kagumachi.repository.MainCategoryRepository;
import tw.com.services.kagumachi.repository.SubCategoryRepository;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private MainCategoryRepository mainCategoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    //增
    public void addMainCategory(MainCategory mainCategory) {
        mainCategoryRepository.save(mainCategory);
    }

    public void addSubCategory(SubCategory subCategory, Integer maincategoryid) {
        MainCategory mainCategory = mainCategoryRepository.findById(maincategoryid)
                .orElseThrow(() -> new IllegalArgumentException("找不到對應的主分類"));

        subCategory.setMainCategory(mainCategory);
        subCategoryRepository.save(subCategory);
    }

    // 刪
    @Transactional
    public void deleteMainCategory(Integer maincategoryid) {
        subCategoryRepository.deleteByMainCategory_Maincategoryid(maincategoryid.longValue());

        mainCategoryRepository.deleteById(maincategoryid);
    }
    public void deleteSubCategory(Integer subcategoryid) {
        subCategoryRepository.deleteById(subcategoryid);
    }

    //修
    public MainCategory updateMainCategory(Integer id, MainCategory updateMain) {

        return mainCategoryRepository.findById(id).map(existingMainCatergory -> {
            existingMainCatergory.setCategoryname(updateMain.getCategoryname());
            existingMainCatergory.setStatus(updateMain.getStatus());
            return mainCategoryRepository.save(existingMainCatergory);
        }).orElseThrow(()-> new RuntimeException("MainCategory not found" + id));
    }

    public SubCategory updateSubCategory(Integer id, SubCategory updateSub) {
        return subCategoryRepository.findById(id).map(existingSubCategory -> {
            existingSubCategory.setCategoryname(updateSub.getCategoryname());
            existingSubCategory.setStatus(updateSub.getStatus());
            existingSubCategory.setMainCategory(updateSub.getMainCategory());
            return subCategoryRepository.save(existingSubCategory);
        }).orElseThrow(() -> new RuntimeException("SubCategory not found: " + id));
    }


    public void updateSubCategory(SubCategory subCategory) {
        if (!subCategoryRepository.existsById(subCategory.getSubcategoryid())) {
            throw new IllegalArgumentException("找不到該子分類，無法更新");
        }
        subCategoryRepository.save(subCategory);
    }

    // 查
    // 取得所有主分類
    public List<MainCategory> getAllMainCategories() {
        return mainCategoryRepository.findAll();
    }

    // 取得特定主分類下的所有子分類
    public List<SubCategory> getSubCategoriesByMainCategoryId(Integer mainCategoryId) {
        if (!mainCategoryRepository.existsById(mainCategoryId)) {
            throw new IllegalArgumentException("找不到該主分類");
        }
        return subCategoryRepository.findByMainCategory_Maincategoryid(mainCategoryId.longValue());
    }
}
