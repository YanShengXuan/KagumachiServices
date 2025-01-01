package tw.com.services.kagumachi.chat.model;

public class InputMessage {
    private String content;

    public InputMessage() {
    }

    public InputMessage(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}