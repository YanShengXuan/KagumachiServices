package tw.com.services.kagumachi.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productid;
    private String productname;
    private String productdescription;

    @ManyToOne
    @JoinColumn(name = "maincategoryid")
    private MainCategory mainCategory;

    @ManyToOne
    @JoinColumn(name = "subcategoryid")
    private SubCategory subCategory;

    @ManyToOne
    @JoinColumn(name = "supplierid")
    private Suppliers supplier;

    private Double width;
    private Double height;
    private Double depth;
    private Integer unitprice;
    private Integer discountprice;
    private Integer productcost;
    private String status;
    private Integer unitsold;
    private Double rating;
    private Integer reviewcount;
    private LocalDate updateat;



    public Suppliers getSupplier() {
        return supplier;
    }

    public void setSupplier(Suppliers supplier) {
        this.supplier = supplier;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductdescription() {
        return productdescription;
    }

    public void setProductdescription(String productdescription) {
        this.productdescription = productdescription;
    }

    public MainCategory getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(MainCategory mainCategory) {
        this.mainCategory = mainCategory;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getDepth() {
        return depth;
    }

    public void setDepth(Double depth) {
        this.depth = depth;
    }

    public Integer getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(Integer unitprice) {
        this.unitprice = unitprice;
    }

    public Integer getDiscountprice() {
        return discountprice;
    }

    public void setDiscountprice(Integer discountprice) {
        this.discountprice = discountprice;
    }

    public Integer getProductcost() {
        return productcost;
    }

    public void setProductcost(Integer productcost) {
        this.productcost = productcost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getUnitsold() {
        return unitsold;
    }

    public void setUnitsold(Integer unitsold) {
        this.unitsold = unitsold;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getReviewcount() {
        return reviewcount;
    }

    public void setReviewcount(Integer reviewcount) {
        this.reviewcount = reviewcount;
    }

    public LocalDate getUpdateat() {
        return updateat;
    }

    public void setUpdateat(LocalDate updateat) {
        this.updateat = updateat;
    }



//  @OneToMany(mappedBy = "product")
//	@JsonBackReference
//	private List<Cart> cart;
//    public List<Cart> getCart() {
//		return cart;
//	}
//	public void setCart(List<Cart> cart) {
//		this.cart = cart;
//	}
//
//	@OneToMany(mappedBy = "product")
//	@JsonManagedReference
//	private List<ProductImage> productimage;
//	public List<ProductImage> getProductimage() {
//		return productimage;
//	}
//	public void setProductimage(List<ProductImage> productimage) {
//		this.productimage = productimage;
//	}

//    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private List<ProductColor> productColors;
//
//    public List<ProductColor> getProductColors() {
//        return productColors;
//    }
//
//    public void setProductColors(List<ProductColor> productColors) {
//        this.productColors = productColors;
//    }
}
