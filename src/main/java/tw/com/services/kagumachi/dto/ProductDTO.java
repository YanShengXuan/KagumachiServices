package tw.com.services.kagumachi.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class ProductDTO implements Serializable {
    private Integer productid;
    private String productname;
    private String productdescription;
    private Integer maincategoryid;
    private Integer subcategoryid;
    private Integer supplierid;
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
    private MainCategoryDTO mainCategory;
    private SubCategoryDTO subCategory;
    private SupplierDTO suppliers;
    private List<ProductColorDTO> productColors;


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

    public Integer getMaincategoryid() {
        return maincategoryid;
    }

    public void setMaincategoryid(Integer maincategoryid) {
        this.maincategoryid = maincategoryid;
    }

    public Integer getSubcategoryid() {
        return subcategoryid;
    }

    public void setSubcategoryid(Integer subcategoryid) {
        this.subcategoryid = subcategoryid;
    }

    public Integer getSupplierid() {
        return supplierid;
    }

    public void setSupplierid(Integer supplierid) {
        this.supplierid = supplierid;
    }

    public MainCategoryDTO getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(MainCategoryDTO mainCategory) {
        this.mainCategory = mainCategory;
    }

    public SubCategoryDTO getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategoryDTO subCategory) {
        this.subCategory = subCategory;
    }





    public SupplierDTO getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(SupplierDTO suppliers) {
        this.suppliers = suppliers;
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

    public List<ProductColorDTO> getProductColors() {
        return productColors;
    }

    public void setProductColors(List<ProductColorDTO> productColors) {
        this.productColors = productColors;
    }

    public static class ProductColorDTO implements Serializable {
        private Integer colorsid;
        private String colorname;
        private Integer stock;
        private Integer minstock;
        private LocalDate updateat;

        private List<ProductImageDTO> productImages;

        public List<ProductImageDTO> getProductImages() {
            return productImages;
        }

        public void setProductImages(List<ProductImageDTO> productImages) {
            this.productImages = productImages;
        }

        public Integer getColorsid() {
            return colorsid;
        }

        public void setColorsid(Integer colorsid) {
            this.colorsid = colorsid;
        }

        public String getColorname() {
            return colorname;
        }

        public void setColorname(String colorname) {
            this.colorname = colorname;
        }

        public Integer getStock() {
            return stock;
        }

        public void setStock(Integer stock) {
            this.stock = stock;
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

    public static class ProductImageDTO implements Serializable {
        private Integer imageid;
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
    }

    public static class MainCategoryDTO implements Serializable {
        private Integer maincategoryid;
        private String categoryname;
        private String status;
        private SalesDTO sales;

        public SalesDTO getSales() {
            return sales;
        }

        public void setSales(SalesDTO sales) {
            this.sales = sales;
        }

        public Integer getMaincategoryid() {
            return maincategoryid;
        }

        public void setMaincategoryid(Integer maincategoryid) {
            this.maincategoryid = maincategoryid;
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

    public static class SubCategoryDTO implements Serializable {
        private Integer subcategoryid;
        private Integer maincategoryid; // 關聯主分類
        private String categoryname;
        private String status;

        public Integer getSubcategoryid() {
            return subcategoryid;
        }

        public void setSubcategoryid(Integer subcategoryid) {
            this.subcategoryid = subcategoryid;
        }

        public Integer getMaincategoryid() {
            return maincategoryid;
        }

        public void setMaincategoryid(Integer maincategoryid) {
            this.maincategoryid = maincategoryid;
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
    public static class SupplierDTO implements Serializable {
        private Integer supplierid;
        private String name;
        private String address;
        private String phone;
        private String contact;
        private String status;

        public Integer getSupplierid() {
            return supplierid;
        }

        public void setSupplierid(Integer supplierid) {
            this.supplierid = supplierid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
    public static class SalesDTO implements Serializable {
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
}
