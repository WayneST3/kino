package dev.neiro.kino.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

/**
 * @author Wayne Stark
 * @since 17.10.2022
 */
@Component
@PropertySource("file:${catalina.base}/conf/kino.properties")
public class JwtTokenUtil {

    /**
     * Время жизни токена в минутах
     */
    public static final int ACCESS_TOKEN_VALIDITY = 120;

    @Value("${jwt.secret}")
    private String secret;

    public String getUsernameFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public Long getUserIdFromToken(String token) {
        return Long.valueOf((Integer)getAllClaimsFromToken(token).get("uid"));
    }

    @SuppressWarnings("unchecked")
    public Collection<String> getPermsFromToken(String token) {
        return (Collection<String>) getAllClaimsFromToken(token).get("perms");
    }

    public Date getExpirationDateFromToken(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public void validateToken(String token) throws SignatureException, ExpiredJwtException {
        Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
    }

    public String generateToken(long userId, String login, String issuer, Set<String> perms) {
        Claims claims = Jwts.claims();
        claims.setSubject(login);
        claims.setIssuer(issuer);
        claims.setIssuedAt(new Date());
        claims.setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY * 60 * 1000));
        claims.put("uid", userId);
        claims.put("perms", (perms != null) ? perms : Collections.emptySet());

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
