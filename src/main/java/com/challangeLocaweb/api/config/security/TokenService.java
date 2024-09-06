package com.challangeLocaweb.api.config.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.challangeLocaweb.api.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${minha.chave.secreta}")
    private String secretkey;

    public String createToken(User user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secretkey);
            String token = JWT.create()
                    .withIssuer("challengerLocaweb")
                    .withSubject(user.getEmail())
                    .withExpiresAt(this.createExpireDate())
                    .sign(algorithm);
            return token;

        }catch (JWTCreationException exception){
            throw  new RuntimeException("Error on create token");
        }
    }


    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secretkey);
            return JWT.require(algorithm)
                    .withIssuer("challengerLocaweb")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException error){
            return "";
        }
    }

    public Instant createExpireDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}