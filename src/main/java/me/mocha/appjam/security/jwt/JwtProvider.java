package me.mocha.appjam.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtProvider {

    private String secret = System.getenv("JWT_SECRET");

    @Value("${jwt.access.exp}")
    private long accessExp;

    @Value("${jwt.refresh.exp}")
    private long refreshExp;

    public JwtProvider() {
        if (!StringUtils.hasText(secret)) secret = "my-secret";
    }

    public String generateToken(String username, JwtType type) {
        return Jwts.builder()
                .setSubject(type.toString())
                .setExpiration(new Date(System.currentTimeMillis() + (type == JwtType.ACCESS ? accessExp : refreshExp)))
                .setIssuedAt(new Date())
                .setNotBefore(new Date())
                .setId(UUID.randomUUID().toString())
                .claim("username", username)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String getUsernameFromJwt(String jwt) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(jwt)
                .getBody().get("username").toString();
    }

    public LocalDateTime getExpiration(String jwt) {
        return LocalDateTime.ofInstant(Jwts.parser().setSigningKey(secret).parseClaimsJws(jwt).getBody().getExpiration().toInstant(), ZoneId.systemDefault());
    }

    public boolean validToken(String jwt, JwtType type) {
        try {
            Jwts.parser().requireSubject(type.toString()).setSigningKey(secret).parseClaimsJws(jwt);
            return true;
        } catch (Exception e) {
            //TODO logging
            return false;
        }
    }

}
