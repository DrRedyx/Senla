package com.example.Senla.Security;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

/**
 * @author Ilyas Nigamatullin
 */
@Component
public class JWTUtil {

  public String generateAccessToken(String username) {
    return JWT.create().
        withSubject("User detail").
        withClaim("username", username).
        withIssuedAt(new Date(System.currentTimeMillis())).
        withIssuer("demo_app").
        withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 24)).
        sign(Algorithm.HMAC256("SECRET"));
  }

  public String validateTokenAndExtractUsername(String token) throws JWTVerificationException {
    JWTVerifier verifier = JWT.require(Algorithm.HMAC256("SECRET")).
        withSubject("User detail").
        withIssuer("demo_app").
        build();

    DecodedJWT jwt = verifier.verify(token);
    return jwt.getClaim("username").asString();
  }
}
