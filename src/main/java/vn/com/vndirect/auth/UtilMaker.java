package vn.com.vndirect.auth;

public class UtilMaker {

    private static final String SECRET_KEY = "I lov3 reading B00k in my Fr33 tim3 0R what^&%";
    private static String secretKey;

    public UtilMaker(String secretKey) {
        this.secretKey = secretKey;
    }

    public static String getSecretKey() {
        return SECRET_KEY;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
