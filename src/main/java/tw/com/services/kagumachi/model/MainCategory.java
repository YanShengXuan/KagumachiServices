package tw.com.services.kagumachi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "MainCategories")
public class MainCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maincategoriesid;

    @ManyToOne
    @JoinColumn(name = "salesId")
    private Sales sales;

    private String categoryname;
    private String status;

    public Integer getMaincategoriesid() {
        return maincategoriesid;
    }

    public void setMaincategoriesid(Integer maincategoriesid) {
        this.maincategoriesid = maincategoriesid;
    }

    public Sales getSales() {
        return sales;
    }

    public void setSales(Sales sales) {
        this.sales = sales;
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
