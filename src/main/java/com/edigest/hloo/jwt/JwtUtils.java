package com.edigest.hloo.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;


@Component
public class JwtUtils {
    private static final Logger logger=LoggerFactory.getLogger(JwtUtils.class);
    @Value("${spring.app.jwtSecret}")
    private String jwtSecret;
    @Value("${spring.app.ExpirationsMs}")
    private int jwtExpirationsMS;

    public String getJwtFromHeader(HttpServletRequest request){
        String bearerToken=request.getHeader("Authorization");
        logger.debug("Authorization Header:{}",bearerToken);
        if(bearerToken!=null&& bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;

    }
    public String generateTokenFromUser(UserDetails userDetails){
        String username=userDetails.getUsername();
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime()+jwtExpirationsMS))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public String getUsernameFromJwtToken(String token){
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
    private Key Key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));

    }
    public boolean validateJwtToken(String authToken){
        try{
            System.out.println("Validate");
            Jwts.parser()
                    .setSigningKey(getSigningKey())
                    .build().parseSignedClaims(authToken);
            return true;
        }
        catch (MalformedJwtException e){
            logger.error("Invalid Jwt Token:{}",e.getMessage());
        }
        catch (ExpiredJwtException e){
            logger.error("Jwt token is expired:{}",e.getMessage());
        }
        catch (IllegalArgumentException e){
            logger.error("Jwt claims string is empty:{} ",e.getMessage());
        }
        catch (UnsupportedJwtException e){
            logger.error("Jwt token is not supported:{}",e.getMessage());
        }
        return false;
    }
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }



}
