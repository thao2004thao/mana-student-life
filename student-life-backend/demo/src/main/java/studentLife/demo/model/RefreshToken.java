package studentLife.demo.model;


import java.util.Date;

public class RefreshToken {
    private String token;
    private String username;
    private Date expiryDate;

    public RefreshToken(String token, String username, Date expiryDate) {
        this.token = token;
        this.username = username;
        this.expiryDate = expiryDate;
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public boolean isExpired() {
        return expiryDate.before(new Date());
    }
}
