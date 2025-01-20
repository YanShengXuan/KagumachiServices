package tw.com.services.kagumachi.chat.model;

import jakarta.persistence.*;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "messageid")
    private Long id;
    private String content;
    private Long senderid;
    private Long receiverid;
    private Long timestamp;
    private boolean isfrontread;
    private boolean isbackread;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getSenderid() {
        return senderid;
    }

    public void setSenderid(Long senderid) {
        this.senderid = senderid;
    }

    public Long getReceiverid() {
        return receiverid;
    }

    public void setReceiverid(Long receiverid) {
        this.receiverid = receiverid;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isIsfrontread() {
        return isfrontread;
    }

    public void setIsfrontread(boolean isfrontread) {
        this.isfrontread = isfrontread;
    }

    public boolean isIsbackread() {
        return isbackread;
    }

    public void setIsbackread(boolean isbackread) {
        this.isbackread = isbackread;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", senderid=" + senderid +
                ", receiverid=" + receiverid +
                ", timestamp=" + timestamp +
                ", isfrontread=" + isfrontread +
                ", isbackread=" + isbackread +
                '}';
    }
}