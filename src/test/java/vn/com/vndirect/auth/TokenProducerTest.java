package vn.com.vndirect.auth;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import vn.com.vndirect.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class TokenProducerTest {
    private TokenProducer producer;
    private Customer customer;

    @Before
    public void setup() {
        String[] audience = new String[] {"audience"};
        producer = new TokenProducer("issuer", "haipham", audience, 5.0f, 5.0f);

        List<String> roles = new ArrayList<>();
        roles.add("ROLE_ADMIN");
        roles.add("ROLE_USER");
        roles.add("ROLE_PREMIUM");

        customer = new Customer("1", "HaiPt", "1", "haipham", roles, "hai.phamthanh@vndirect.com.vn");
    }

    @Test
    public void testCreateTokenJwt() throws Exception {
        String token = producer.token(customer);

        System.out.println(token);
        Assert.assertNotNull(token);
    }
}