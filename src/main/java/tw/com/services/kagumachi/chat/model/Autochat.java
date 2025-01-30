package tw.com.services.kagumachi.chat.model;

import jakarta.persistence.*;

@Entity
public class Autochat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long autochatid;
    private String keywords;
    private String answer;
    private Boolean isautochat;

    public Long getAutochatid() {
        return autochatid;
    }

    public void setAutochatid(Long autochatid) {
        this.autochatid = autochatid;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean getIsautochat() {
        return isautochat;
    }

    public void setIsautochat(Boolean isautochat) {
        this.isautochat = isautochat;
    }
}
