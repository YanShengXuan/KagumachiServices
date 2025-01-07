package tw.com.services.kagumachi.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "IncomeStatement")
public class IncomeStatement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer incomeStatementId;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    private LocalDate month;

    private Integer productCost;
    private Integer operatingRevenue;
    private Integer operatingExpenses;
    private Integer grossProfit;
    private Integer nonOperatingIncomeAndExpenses;
    private Integer incomeTax;
    private Integer netProfitAfterTax;
    private Integer capitalStock;
    private Integer earningsPerShare;

    public Integer getIncomeStatementId() {
        return incomeStatementId;
    }

    public void setIncomeStatementId(Integer incomeStatementId) {
        this.incomeStatementId = incomeStatementId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDate getMonth() {
        return month;
    }

    public void setMonth(LocalDate month) {
        this.month = month;
    }

    public Integer getProductCost() {
        return productCost;
    }

    public void setProductCost(Integer productCost) {
        this.productCost = productCost;
    }

    public Integer getOperatingRevenue() {
        return operatingRevenue;
    }

    public void setOperatingRevenue(Integer operatingRevenue) {
        this.operatingRevenue = operatingRevenue;
    }

    public Integer getOperatingExpenses() {
        return operatingExpenses;
    }

    public void setOperatingExpenses(Integer operatingExpenses) {
        this.operatingExpenses = operatingExpenses;
    }

    public Integer getGrossProfit() {
        return grossProfit;
    }

    public void setGrossProfit(Integer grossProfit) {
        this.grossProfit = grossProfit;
    }

    public Integer getNonOperatingIncomeAndExpenses() {
        return nonOperatingIncomeAndExpenses;
    }

    public void setNonOperatingIncomeAndExpenses(Integer nonOperatingIncomeAndExpenses) {
        this.nonOperatingIncomeAndExpenses = nonOperatingIncomeAndExpenses;
    }

    public Integer getIncomeTax() {
        return incomeTax;
    }

    public void setIncomeTax(Integer incomeTax) {
        this.incomeTax = incomeTax;
    }

    public Integer getNetProfitAfterTax() {
        return netProfitAfterTax;
    }

    public void setNetProfitAfterTax(Integer netProfitAfterTax) {
        this.netProfitAfterTax = netProfitAfterTax;
    }

    public Integer getCapitalStock() {
        return capitalStock;
    }

    public void setCapitalStock(Integer capitalStock) {
        this.capitalStock = capitalStock;
    }

    public Integer getEarningsPerShare() {
        return earningsPerShare;
    }

    public void setEarningsPerShare(Integer earningsPerShare) {
        this.earningsPerShare = earningsPerShare;
    }
}