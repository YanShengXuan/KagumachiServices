package tw.com.services.kagumachi.chat.model;

public class InputMessage {
    private String content;
    private String senderid;
    private String receiverid;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSenderid() {
        return senderid;
    }

    public void setSenderid(String senderid) {
        this.senderid = senderid;
    }

    public String getReceiverid() {
        return receiverid;
    }

    public void setReceiverid(String receiverid) {
        this.receiverid = receiverid;
    }

    @Override
    public String toString() {
        return "InputMessage{" +
                "content='" + content + '\'' +
                ", senderid='" + senderid + '\'' +
                ", receiverid='" + receiverid + '\'' +
                '}';
    }
}