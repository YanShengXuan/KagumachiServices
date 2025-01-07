package tw.com.services.kagumachi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Shiprate")
public class ShipRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shipRateId;

    private String region;

    private Integer rate;


    public Integer getShipRateId() {
        return shipRateId;
    }

    public void setShipRateId(Integer shipRateId) {
        this.shipRateId = shipRateId;
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