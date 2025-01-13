package tw.com.services.kagumachi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "shiprate")
public class ShipRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shiprateid;

    private String region;

    private Integer rate;


    public Integer getShiprateid() {
        return shiprateid;
    }

    public void setShiprateid(Integer shiprateid) {
        this.shiprateid = shiprateid;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
}