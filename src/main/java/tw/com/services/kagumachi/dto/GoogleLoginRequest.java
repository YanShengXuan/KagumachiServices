package tw.com.services.kagumachi.dto;

public class GoogleLoginRequest {

    private String email;
    private String googleId;

    public GoogleLoginRequest() {
    }

    public GoogleLoginRequest(String email, String googleId) {
        this.email = email;
        this.googleId = googleId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }
}
