package vn.com.vndirect.auth;

import org.jose4j.jwt.JwtClaims;
import vn.com.vndirect.customer.Customer;
import vn.com.vndirect.util.JwtClaimKey;

import java.util.HashMap;
import java.util.Map;

public class TokenProducer {
    private String issuer;
    private String subject;
    private String[] audience;
    private float expiration;
    private float notBefore;
    private JWTIssuer jwtIssuer;

    public TokenProducer(String issuer, String subject, String[] audience, float expiration, float notBefore) {
        this.issuer = issuer;
        this.subject = subject;
        this.audience = audience;
        this.expiration = expiration;
        this.notBefore = notBefore;
        this.jwtIssuer = new JWTIssuer(UtilMaker.getSecretKey());
    }

    public String token(Customer customer) {
        Map<String, String> claims = new HashMap<>();
        claims.put(JwtClaimKey.CUSTOMER_NAME.value(), customer.getCustomerName());
        claims.put(JwtClaimKey.CUSTOMER_ID.value(), customer.getCustomerId());
        claims.put(JwtClaimKey.USER_ID.value(), customer.getUserId());
        claims.put(JwtClaimKey.USER_NAME.value(), customer.getUsername());
        // Code below is commented-out because hibernate doesn't save List type
        //claims.put(JwtClaimKey.ROLES.value(), customer.getRoles().toString());
        claims.put(JwtClaimKey.EMAIL.value(), customer.getEmail());

        return produce(claims);
    }

    private String produce(Map<String, String> claims) {
        JwtClaims jwtClaims = new JwtClaims();

        jwtClaims.setIssuer(issuer);
        jwtClaims.setSubject(subject);
        jwtClaims.setAudience(audience);
        jwtClaims.setGeneratedJwtId();
        jwtClaims.setIssuedAtToNow();
        jwtClaims.setExpirationTimeMinutesInTheFuture(expiration);
        jwtClaims.setNotBeforeMinutesInThePast(notBefore);

        for (Map.Entry<String, String> entry : claims.entrySet()) {
            jwtClaims.setClaim(entry.getKey(), entry.getValue());
        }

        return jwtIssuer.createToken(jwtClaims);
    }

}
