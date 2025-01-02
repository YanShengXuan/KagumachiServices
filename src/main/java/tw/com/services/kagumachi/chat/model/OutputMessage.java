package tw.com.services.kagumachi.chat.model;

public class OutputMessage {
    private String content;
    private String senderid;
    private String receiverid;

    public OutputMessage() {}

    public OutputMessage(String escapedContent, String senderid, String receiverid) {
        this.content = escapedContent;
        this.senderid = senderid;
        this.receiverid = receiverid;
    }

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
        return "OutputMessage{" +
                "content='" + content + '\'' +
                ", senderid='" + senderid + '\'' +
                ", receiverid='" + receiverid + '\'' +
                '}';
    }
}