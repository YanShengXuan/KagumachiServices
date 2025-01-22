package tw.com.services.kagumachi.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderid;

	@ManyToOne
	@JoinColumn(name = "memberid")
	private Member member;

	private String orderstatus;
	private String paymentmethod;
	private String shippingmethod;
	private String ordercity;
	private String recipient;
	private String phone;
	private String district;
	private String address;
	private LocalDate orderdate;
	private LocalDate deliverydate;
	private Double totalprice;
	private String orderserial;

	@ManyToOne
	@JoinColumn(name = "logisticsid")
	private Logistics logistics;

	private String logisticsnumber;
	private LocalDate estimateddeliverydate;

	public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}

	public String getPaymentmethod() {
		return paymentmethod;
	}

	public void setPaymentmethod(String paymentmethod) {
		this.paymentmethod = paymentmethod;
	}

	public String getShippingmethod() {
		return shippingmethod;
	}

	public void setShippingmethod(String shippingmethod) {
		this.shippingmethod = shippingmethod;
	}

	public String getOrdercity() {
		return ordercity;
	}

	public void setOrdercity(String ordercity) {
		this.ordercity = ordercity;
	}

	public LocalDate getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(LocalDate orderdate) {
		this.orderdate = orderdate;
	}

	public LocalDate getDeliverydate() {
		return deliverydate;
	}

	public void setDeliverydate(LocalDate deliverydate) {
		this.deliverydate = deliverydate;
	}

	public Double getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(Double totalprice) {
		this.totalprice = totalprice;
	}

	public Logistics getLogistics() {
		return logistics;
	}

	public void setLogistics(Logistics logistics) {
		this.logistics = logistics;
	}

	public String getLogisticsnumber() {
		return logisticsnumber;
	}

	public void setLogisticsnumber(String logisticsnumber) {
		this.logisticsnumber = logisticsnumber;
	}

	public LocalDate getEstimateddeliverydate() {
		return estimateddeliverydate;
	}

	public void setEstimateddeliverydate(LocalDate estimateddeliverydate) {
		this.estimateddeliverydate = estimateddeliverydate;
	}

	public String getOrderserial() {
		return orderserial;
	}

	public void setOrderserial(String orderserial) {
		this.orderserial = orderserial;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	// 處理NULL值
	@Override
	public String toString() {
	    return "Order{" +
	            "orderstatus=" + orderstatus +
	            ", totalprice=" + totalprice +
	            "}";
	}
}
