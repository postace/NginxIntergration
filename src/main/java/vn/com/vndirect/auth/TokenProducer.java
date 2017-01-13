package vn.com.vndirect.auth;

import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;
import vn.com.vndirect.model.Customer;
import vn.com.vndirect.util.JwtClaimKey;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

public class TokenProducer {
    public static final String SECRET_KEY = "I lov3 reading B00k in my Fr33 tim3 0R what^&%";
    private String issuer;
    private String subject;
    private String[] audience;
    private float expiration;
    private float notBefore;

    public TokenProducer(String issuer, String subject, String[] audience, float expiration, float notBefore) {
        this.issuer = issuer;
        this.subject = subject;
        this.audience = audience;
        this.expiration = expiration;
        this.notBefore = notBefore;
    }

    public String token(Customer customer) {
        Map<String, String> claims = new HashMap<>();
        claims.put(JwtClaimKey.CUSTOMER_NAME.value(), customer.getCustomerName());
        claims.put(JwtClaimKey.CUSTOMER_ID.value(), customer.getCustomerId());
        claims.put(JwtClaimKey.USER_ID.value(), customer.getUserId());
        claims.put(JwtClaimKey.USER_NAME.value(), customer.getUsername());
        claims.put(JwtClaimKey.EMAIL.value(), customer.getEmail());

        return produce(claims);
    }

    public String produce(Map<String, String> claims) {
        JwtClaims jwtClaims = new JwtClaims();

        jwtClaims.setIssuer(issuer);
        jwtClaims.setSubject(subject);
        jwtClaims.setAudience(audience);
        jwtClaims.setExpirationTimeMinutesInTheFuture(expiration);
        jwtClaims.setNotBeforeMinutesInThePast(notBefore);

        for (Map.Entry<String, String> entry : claims.entrySet()) {
            jwtClaims.setClaim(entry.getKey(), entry.getValue());
        }

        return createToken(jwtClaims);
    }

    public String createToken(JwtClaims claims) {
        JsonWebSignature jws = new JsonWebSignature();

        jws.setHeader("typ", "JWT");
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.HMAC_SHA256);
        jws.setPayload(claims.toJson());
        jws.setKey(createKeyFromString(SECRET_KEY));

        try {
            return jws.getCompactSerialization();
        } catch (JoseException e) {
            throw new RuntimeException(e);
        }
    }

    private Key createKeyFromString(String keyString) {
        return new HmacKey(keyString.getBytes());
    }

    public String produceJwtToken() throws JoseException {
        Key key = new HmacKey(SECRET_KEY.getBytes());

        JwtClaims claims = new JwtClaims();
        claims.setSubject("pete");
        claims.setClaim("iat", 1430677663);

        JsonWebSignature jws = new JsonWebSignature();
        jws.setHeader("typ", "JWT");
        jws.setPayload(claims.toJson());
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.HMAC_SHA256);
        jws.setKey(key);

        return jws.getCompactSerialization();
    }

}
