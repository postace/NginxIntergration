package vn.com.vndirect.auth;

import org.apache.log4j.Level;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;
import vn.com.vndirect.util.LogUtil;

import java.security.Key;

public class JWTIssuer {

    private String secretKey;

    public JWTIssuer(String secret) {
        this.secretKey = secret;
    }

    public String createToken(JwtClaims claims) {
        JsonWebSignature jws = new JsonWebSignature();
        // Must add header below to satisfy current nginx-jwt verifier
        jws.setHeader("typ", "JWT");
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.HMAC_SHA256);
        jws.setPayload(claims.toJson());
        jws.setKey(createKeyFromString(secretKey));

        try {
            return jws.getCompactSerialization();
        } catch (JoseException e) {
            LogUtil.log(Level.WARN, this, "Couldn't generate token %s", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private Key createKeyFromString(String key) {
        return new HmacKey(key.getBytes());
    }
}
