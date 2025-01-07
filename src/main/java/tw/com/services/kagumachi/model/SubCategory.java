package tw.com.services.kagumachi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "SubCategories")
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer subcategoryid;

    @ManyToOne
    @JoinColumn(name = "mainCategoriesId")
    private MainCategory mainCategory;

    private String categoryname;
    private String status;

    public Integer getSubcategoryid() {
        return subcategoryid;
    }

    public void setSubcategoryid(Integer subcategoryid) {
        this.subcategoryid = subcategoryid;
    }

    public MainCategory getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(MainCategory mainCategory) {
        this.mainCategory = mainCategory;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
