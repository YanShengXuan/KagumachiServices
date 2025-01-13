package tw.com.services.kagumachi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "maincategory")
public class MainCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maincategoryid;

    @ManyToOne
    @JoinColumn(name = "salesid")
    private Sales sales;

    private String categoryname;
    private String status;

    public Integer getMaincategoryid() {
        return maincategoryid;
    }

    public void setMaincategoryid(Integer maincategoryid) {
        this.maincategoryid = maincategoryid;
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
