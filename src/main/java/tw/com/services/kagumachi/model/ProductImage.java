package tw.com.services.kagumachi.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "productimages")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer imageid;

    @ManyToOne
    @JoinColumn(name = "productid")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "colorsid")
    private ProductColor productColor;


    private String imageurl;
    private Boolean isprimary;
    private LocalDate updatedat;

    public Integer getImageid() {
        return imageid;
    }

    public void setImageid(Integer imageid) {
        this.imageid = imageid;
    }


    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public Boolean getIsprimary() {
        return isprimary;
    }

    public void setIsprimary(Boolean isprimary) {
        this.isprimary = isprimary;
    }

    public LocalDate getUpdatedat() {
        return updatedat;
    }

    public void setUpdatedat(LocalDate updatedat) {
        this.updatedat = updatedat;
    }


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    

    public ProductColor getProductColor() {
        return productColor;
    }

    public void setProductColor(ProductColor productColor) {
        this.productColor = productColor;
    }
}
