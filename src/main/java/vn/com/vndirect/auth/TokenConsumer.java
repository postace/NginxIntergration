package vn.com.vndirect.auth;

import org.jose4j.jwt.consumer.InvalidJwtException;
import vn.com.vndirect.customer.Customer;
import vn.com.vndirect.util.JwtClaimKey;

import java.util.Map;

public class TokenConsumer {

    private JWTParser jwtParser;

    public TokenConsumer(String secretKey) {
        jwtParser = new JWTParser(secretKey);
    }

    public TokenConsumer(String issuer, String secretKey) {
        jwtParser = new JWTParser(issuer, secretKey);
    }

    public Customer consume(String token) throws InvalidJwtException {
        Map<String, Object> claimed = jwtParser.parseTokenNoValidate(token);

        Customer customer = new Customer();
        customer.setCustomerId((String) claimed.get(JwtClaimKey.CUSTOMER_ID.value()));
        customer.setCustomerName((String) claimed.get(JwtClaimKey.CUSTOMER_NAME.value()));
        customer.setUserId((String) claimed.get(JwtClaimKey.USER_ID.value()));
        customer.setUsername((String) claimed.get(JwtClaimKey.CUSTOMER_NAME.value()));
        customer.setEmail((String) claimed.get(JwtClaimKey.EMAIL.value()));

        return customer;
    }

}
