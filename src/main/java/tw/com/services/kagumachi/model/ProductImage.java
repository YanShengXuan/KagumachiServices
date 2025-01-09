package tw.com.services.kagumachi.model;

import jakarta.persistence.*;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Productimages")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer imageid;

    private String imageurl;
    private Boolean isprimary;
    private Date updatedat;

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

    public Date getUpdatedat() {
        return updatedat;
    }

    public void setUpdatedat(Date updatedat) {
        this.updatedat = updatedat;
    }
    
    @ManyToOne
    @JoinColumn(name = "productid")
    @JsonBackReference
    private Product product;
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    @ManyToOne
    @JoinColumn(name = "colorsid")
    @JsonManagedReference
    private ProductColor productColor;
    public ProductColor getProductColor() {
        return productColor;
    }

    public void setProductColor(ProductColor productColor) {
        this.productColor = productColor;
    }
}
