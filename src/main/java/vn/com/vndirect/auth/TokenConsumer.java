package vn.com.vndirect.auth;

import org.apache.log4j.Level;
import org.jose4j.jwt.consumer.InvalidJwtException;
import vn.com.vndirect.customer.Customer;
import vn.com.vndirect.util.JwtClaimKey;
import vn.com.vndirect.util.LogUtil;

import java.util.Map;

public class TokenConsumer {

    private JWTParser jwtParser;

    public TokenConsumer(String issuer, String secretKey) {
        jwtParser = new JWTParser(issuer, secretKey);
    }

    public Customer consume(String token) throws InvalidJwtException {
        // Split token because token has format: 'Bearer token'
        String[] header = token.split("Bearer ");
        String realToken = header[1];
        Map<String, Object> claimed = jwtParser.parseTokenNoValidate(realToken);

        Customer customer = new Customer();
        customer.setCustomerId((String) claimed.get(JwtClaimKey.CUSTOMER_ID.value()));
        customer.setCustomerName((String) claimed.get(JwtClaimKey.CUSTOMER_NAME.value()));
        customer.setUserId((String) claimed.get(JwtClaimKey.USER_ID.value()));
        customer.setUsername((String) claimed.get(JwtClaimKey.CUSTOMER_NAME.value()));
        customer.setEmail((String) claimed.get(JwtClaimKey.EMAIL.value()));

        return customer;
    }

}
