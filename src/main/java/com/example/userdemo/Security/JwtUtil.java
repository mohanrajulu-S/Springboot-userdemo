package com.example.userdemo.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;


@Component
public class JwtUtil
{
    private static final String SECRET_KEY="mysecretkeymysecretkeymysecretkeymysecretkey";
    private  static final long EXPIRATION_TIME=1000*60*60;     //1 hour =1000*60*60

    private SecretKey getSigningKey()
    {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }


    //Generate JWT token
    public String generateToken(String username,String role)
    {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .claim("role",role)
                .expiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .signWith(getSigningKey())
                .compact();
    }

    //Parse claims or Extract claims
    private Claims getClaims(String token)
    {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }

    // Extract Username
    public String extractUsername(String token)
    {
        return getClaims(token).getSubject();
    }




    public String  extractRole(String token)
    {
        return getClaims(token).get("role",String.class);
    }

    // Validate Token
    public boolean  validateToken(String token)
    {
        return getClaims(token)
                .getExpiration()
                .before(new Date(System.currentTimeMillis()+EXPIRATION_TIME));
    }



//    //Validate Token
//    public boolean validateToken(String token)
//    {
//        try
//        {
//            getClaims(token);
//            return true;
//        }
//        catch(Exception e)
//        {
//            return false;
//        }
//
//    }

}
