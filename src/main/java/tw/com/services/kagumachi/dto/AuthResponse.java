package tw.com.services.kagumachi.dto;

public class AuthResponse {

    private String token;
    private Integer memberId;

    public AuthResponse(String token, Integer memberId) {
        this.token = token;
        this.memberId = memberId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }
}
