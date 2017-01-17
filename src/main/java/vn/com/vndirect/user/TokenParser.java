package vn.com.vndirect.user;

import org.jose4j.jwt.consumer.InvalidJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import vn.com.vndirect.auth.TokenConsumer;
import vn.com.vndirect.customer.Customer;

@Component
public class TokenParser {
    private TokenConsumer consumer;

    @Autowired
    public TokenParser(TokenConsumer consumer) {
        this.consumer = consumer;
    }

    public Authentication getAuthentication(String token) throws InvalidJwtException {
        UserAuthentication userAuth = null;
        if (token != null) {
            Customer customer = consumer.consume(token);
            UserDetails user = new UserDetails();
            user.setCustomer(customer);
            userAuth = new UserAuthentication(user);
        }
        return userAuth;
    }
}
