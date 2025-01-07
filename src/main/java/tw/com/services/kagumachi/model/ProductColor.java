package tw.com.services.kagumachi.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ProductColors")
public class ProductColor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer colorsId;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;


    private String colorname;
    private Integer stock;
    private Integer minstock;
    private LocalDate updateat;

    public Integer getColorsId() {
        return colorsId;
    }

    public void setColorsId(Integer colorsId) {
        this.colorsId = colorsId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getColorname() {
        return colorname;
    }

    public void setColorname(String colorname) {
        this.colorname = colorname;
    }

    public Integer getMinstock() {
        return minstock;
    }

    public void setMinstock(Integer minstock) {
        this.minstock = minstock;
    }

    public LocalDate getUpdateat() {
        return updateat;
    }

    public void setUpdateat(LocalDate updateat) {
        this.updateat = updateat;
    }
}