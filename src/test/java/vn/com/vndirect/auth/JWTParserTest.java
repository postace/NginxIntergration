package vn.com.vndirect.auth;

import org.jose4j.jwt.consumer.InvalidJwtException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import vn.com.vndirect.customer.Customer;

public class JWTParserTest {

    private static final String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ2bmRpcmVjdCIsInN1YiI6ImpvaG5kb2UiLCJhdWQiOiJhdWRpZW5jZSIsImp0aSI6IjA3eXZiMlNmM3djbGZjQlVUeFN0WmciLCJpYXQiOjE0ODQ0NDU1MzgsImV4cCI6MTQ4NDQ3NDMzOCwibmJmIjoxNDg0NDQ1NDE4LCJjdXN0b21lcklkIjoiMTExMSIsInVzZXJOYW1lIjoiam9obmRvZSIsInVzZXJJZCI6IjExMSIsImN1c3RvbWVyTmFtZSI6IkpvaG5Eb2UiLCJlbWFpbCI6ImpvaG5kb2VAZ21haWwuY29tIn0.wLqCQc70iioipJ40ceMTM8feei07wfLn6oDbw0YFJsc";
    private TokenConsumer tokenConsumer;

    @Before
    public void setup() {
        tokenConsumer = new TokenConsumer("vndirect", UtilMaker.getSecretKey());
    }

    @Test
    public void testValidateToken() throws InvalidJwtException {
        Customer customer = tokenConsumer.consume(token);

        Assert.assertNotNull(customer);
    }

    // TODO: Test parse token without audience
    // TODO: Test parse token expired
    // TODO: Test parse token with invalid issuer
}