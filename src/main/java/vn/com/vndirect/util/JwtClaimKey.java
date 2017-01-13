package vn.com.vndirect.util;

public enum  JwtClaimKey {
    CUSTOMER_ID("customerId"),
    CUSTOMER_NAME("customerName"),
    USER_ID("userId"),
    USER_NAME("userName"),
    ROLES("roles"),
    EMAIL("email");

    private String value;

    JwtClaimKey(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
