package org.example.service;


import io.jsonwebtoken.Claims;

import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.function.Function;

@Service
public class JwtService {


    public static final String SECRET="mgkbbbbbbhnfdalfbbbbbbbbgfnbkdskbndelkrthris";

    public String extractUsername(String token){
        return extractCliams(token, Claims::getSubject);
    }

    public <T> T extractClaims(String token, Function<T,Claims>ClaimsResolver){
        final Claims claim=extractAllCliams(token);
        return ClaimsResolver.apply(claim);

    }
    public Claims extractAllCliams(String token){
        return Jwts.
                parser()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key  getSignKey(){
        byte[] keyBytes= Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
