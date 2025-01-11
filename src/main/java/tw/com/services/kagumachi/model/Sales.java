package tw.com.services.kagumachi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "sales")
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer salesid;

    private String name;

    private String salesdesc;

    private Double discount;

    public Integer getSalesid() {
        return salesid;
    }

    public void setSalesid(Integer salesid) {
        this.salesid = salesid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalesdesc() {
        return salesdesc;
    }

    public void setSalesdesc(String salesdesc) {
        this.salesdesc = salesdesc;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}