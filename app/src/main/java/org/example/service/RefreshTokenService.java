package org.example.service;

import org.example.enities.RefreshToken;
import org.example.enities.UserInfo;
import org.example.repositary.RefreshTokenRepositary;
import org.example.repositary.UserRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepositary refreshTokenRepositary;

    @Autowired
    private UserRepositary userRepositary;

    public RefreshToken createRefreshToken(String username){
        UserInfo userInfoExtracted = userRepositary.findByUsername(username);
        RefreshToken refreshToken = RefreshToken.builder()
                .userInfo(userInfoExtracted)
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(600000))
                .build();
        return refreshTokenRepositary.save(refreshToken);
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepositary.findByToken(token);
    }
    public RefreshToken verifyExpiration(RefreshToken token){
        if(token.getExpiryDate().compareTo(Instant.now())<0){
            refreshTokenRepositary.delete(token);
            throw new RuntimeException(token.getToken() + " Refresh token is expired. Please make a new login..!");
        }
        return token;
    }




}
