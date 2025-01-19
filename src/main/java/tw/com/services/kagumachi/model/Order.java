package tw.com.services.kagumachi.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

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
    
}
