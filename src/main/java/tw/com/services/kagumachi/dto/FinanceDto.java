package tw.com.services.kagumachi.dto;

import java.time.LocalDate;

public class FinanceDto {
	private LocalDate startDate;
	private LocalDate endDate;
	private String item;
	private String details;
	private Integer money;
	private LocalDate date;
	private Integer financeid;
	
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
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Integer getFinanceid() {
		return financeid;
	}
	public void setFinanceid(Integer financeid) {
		this.financeid = financeid;
	}
	
	
}
