package tw.com.services.kagumachi.model;

import jakarta.persistence.*;

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
    private Date orderdate;
    private Date deliverydate;
    private Double totalprice;

    @ManyToOne
    @JoinColumn(name = "logisticsid")
    private Logistics logistics;

    private String logisticsnumber;
    private Date estimateddeliverydate;

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

    public void setPaymentmethodid(String paymentmethod) {
        this.paymentmethod = paymentmethod;
    }

    public String getShippingmethod() {
        return shippingmethod;
    }

    public void setShippingmethodid(String shippingmethod) {
        this.shippingmethod = shippingmethod;
    }

    public String getOrdercity() {
        return ordercity;
    }

    public void setOrdercity(String ordercity) {
        this.ordercity = ordercity;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public Date getDeliverydate() {
        return deliverydate;
    }

    public void setDeliverydate(Date deliverydate) {
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

    public Date getEstimateddeliverydate() {
        return estimateddeliverydate;
    }

    public void setEstimateddeliverydate(Date estimateddeliverydate) {
        this.estimateddeliverydate = estimateddeliverydate;
    }
}
