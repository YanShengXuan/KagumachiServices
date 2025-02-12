package tw.com.services.kagumachi.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "finance")
public class Finance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer financeid;

    private LocalDate date;

    private String item;

    private String details;

    private Integer money;

    public Integer getFinanceid() {
        return financeid;
    }

    public void setFinanceid(Integer financeid) {
        this.financeid = financeid;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }
}