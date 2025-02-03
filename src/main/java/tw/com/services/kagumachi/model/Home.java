package tw.com.services.kagumachi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Home {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer homeid;

    private Integer memberid;
    private Double doorwidth;
    private Double doorheight;
    private Double elevatorwidth;
    private Double elevatorheight;
    private Double elevatordepth;
    private Double stairwidth;
    private Double stairheight;

    public Integer getHomeid() {
        return homeid;
    }

    public void setHomeid(Integer homeid) {
        this.homeid = homeid;
    }

    public Integer getMemberid() {
        return memberid;
    }

    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }

    public Double getDoorwidth() {
        return doorwidth;
    }

    public void setDoorwidth(Double doorwidth) {
        this.doorwidth = doorwidth;
    }

    public Double getDoorheight() {
        return doorheight;
    }

    public void setDoorheight(Double doorheight) {
        this.doorheight = doorheight;
    }

    public Double getElevatorwidth() {
        return elevatorwidth;
    }

    public void setElevatorwidth(Double elevatorwidth) {
        this.elevatorwidth = elevatorwidth;
    }

    public Double getElevatorheight() {
        return elevatorheight;
    }

    public void setElevatorheight(Double elevatorheight) {
        this.elevatorheight = elevatorheight;
    }

    public Double getElevatordepth() {
        return elevatordepth;
    }

    public void setElevatordepth(Double elevatordepth) {
        this.elevatordepth = elevatordepth;
    }

    public Double getStairwidth() {
        return stairwidth;
    }

    public void setStairwidth(Double stairwidth) {
        this.stairwidth = stairwidth;
    }

    public Double getStairheight() {
        return stairheight;
    }

    public void setStairheight(Double stairheight) {
        this.stairheight = stairheight;
    }
}
