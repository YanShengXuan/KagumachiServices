package tw.com.services.kagumachi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "IncomeStatement")
public class IncomeStatement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer incomestatementid;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    private Integer month;
    private Integer year;

    public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	private Integer productcost;
    private Integer operatingrevenue;
    private Integer operatingexpenses;
    private Integer grossprofit;
    private Integer nonoperatingincomeandexpenses;
    private Integer incometax;
    private Integer netprofitaftertax;
    private Integer capitalstock;
    private Integer earningspershare;

    public Integer getIncomestatementid() {
        return incomestatementid;
    }

    public void setIncomestatementid(Integer incomestatementid) {
        this.incomestatementid = incomestatementid;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public Integer getProductcost() {
        return productcost;
    }

    public void setProductcost(Integer productcost) {
        this.productcost = productcost;
    }

    public Integer getOperatingrevenue() {
        return operatingrevenue;
    }

    public void setOperatingrevenue(Integer operatingrevenue) {
        this.operatingrevenue = operatingrevenue;
    }

    public Integer getOperatingexpenses() {
        return operatingexpenses;
    }

    public void setOperatingexpenses(Integer operatingexpenses) {
        this.operatingexpenses = operatingexpenses;
    }

    public Integer getGrossprofit() {
        return grossprofit;
    }

    public void setGrossprofit(Integer grossprofit) {
        this.grossprofit = grossprofit;
    }

    public Integer getNonoperatingincomeandexpenses() {
        return nonoperatingincomeandexpenses;
    }

    public void setNonoperatingincomeandexpenses(Integer nonoperatingincomeandexpenses) {
        this.nonoperatingincomeandexpenses = nonoperatingincomeandexpenses;
    }

    public Integer getIncometax() {
        return incometax;
    }

    public void setIncometax(Integer incometax) {
        this.incometax = incometax;
    }

    public Integer getNetprofitaftertax() {
        return netprofitaftertax;
    }

    public void setNetprofitaftertax(Integer netprofitaftertax) {
        this.netprofitaftertax = netprofitaftertax;
    }

    public Integer getCapitalstock() {
        return capitalstock;
    }

    public void setCapitalstock(Integer capitalstock) {
        this.capitalstock = capitalstock;
    }

    public Integer getEarningspershare() {
        return earningspershare;
    }

    public void setEarningspershare(Integer earningspershare) {
        this.earningspershare = earningspershare;
    }
}