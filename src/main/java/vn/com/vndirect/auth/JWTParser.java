package vn.com.vndirect.auth;

import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.HmacKey;

import java.security.Key;
import java.util.Map;

public class JWTParser {

    private String officialIssuer;
    private String secretKey;

    public JWTParser(String secretKey) {
        this.secretKey = secretKey;
    }

    public JWTParser(String issuer, String secretKey) {
        this.officialIssuer = issuer;
        this.secretKey = secretKey;
    }

    @SuppressWarnings("unused")
    public Map<String, Object> parseToken(String JWTToken) throws InvalidJwtException {

        Key verificationKey = new HmacKey(secretKey.getBytes());
        JwtConsumer secondPassJwtConsumer = new JwtConsumerBuilder()
                .setExpectedIssuer(officialIssuer)
                .setSkipDefaultAudienceValidation()
                .setVerificationKey(verificationKey)
                .setRequireExpirationTime()
                .setAllowedClockSkewInSeconds(30)
                .setRequireSubject()
                .build();
        JwtClaims jwtClaims = secondPassJwtConsumer.processToClaims(JWTToken);

        return jwtClaims.getClaimsMap();
    }

    public Map<String, Object> parseTokenNoValidate(String JWTToken) throws InvalidJwtException {
        Key verificationKey = new HmacKey(secretKey.getBytes());
        JwtConsumer consumer = new JwtConsumerBuilder()
                .setVerificationKey(verificationKey)
                .setSkipAllValidators().build();
        JwtClaims claims = consumer.processToClaims(JWTToken);

        return claims.getClaimsMap();
    }
}
