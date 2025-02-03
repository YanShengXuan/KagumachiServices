package tw.com.services.kagumachi.model;

import java.sql.Timestamp;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "incomestatement")
public class IncomeStatement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "startdate", nullable = false)
    private LocalDate startDate;

    @Column(name = "enddate", nullable = false)
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "maincategoryid", nullable = false)
    private MainCategory mainCategory;

    @ManyToOne
    @JoinColumn(name = "subcategoryid", nullable = false)
    private SubCategory subCategory;

    @Column(name = "totalrevenue", nullable = false)
    private Double totalRevenue;

    @Column(name = "totalcost", nullable = false)
    private Double totalCost;

    // STORED GENERATED Columns，只讀不寫
    @Column(name = "operatingprofit", insertable = false, updatable = false)
    private Double operatingProfit;

    @Column(name = "nonoperatingincomeandexpense", nullable = false)
    private Double nonOperatingIncomeAndExpense;

    @Column(name = "incomebeforetax", insertable = false, updatable = false)
    private Double incomeBeforeTax;

    @Column(name = "tax", nullable = false)
    private Double tax;

    @Column(name = "netincome", insertable = false, updatable = false)
    private Double netIncome;

    // 只在資料庫自動產生，不允許自行更新
    @Column(name = "createdat", nullable = false, insertable = false, updatable = false)
    private Timestamp createdAt;

    // 建構子
    public IncomeStatement() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
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

	public Double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Double getOperatingProfit() {
        return operatingProfit;
    }

    // operatingProfit 不主動提供 setter，因為是資料庫自動生成
    // 若真有需要自行計算并存回，確定 DB 設定允許更新

    public Double getNonOperatingIncomeAndExpense() {
        return nonOperatingIncomeAndExpense;
    }

    public void setNonOperatingIncomeAndExpense(Double nonOperatingIncomeAndExpense) {
        this.nonOperatingIncomeAndExpense = nonOperatingIncomeAndExpense;
    }

    public Double getIncomeBeforeTax() {
        return incomeBeforeTax;
    }

    // incomeBeforeTax 不主動提供 setter

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getNetIncome() {
        return netIncome;
    }

    // netIncome 不主動提供 setter

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    // createdAt 不主動提供 setter
}