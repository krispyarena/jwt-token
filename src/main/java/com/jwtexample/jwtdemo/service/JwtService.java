package com.jwtexample.jwtdemo.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.function.Function;

@Service
public class JwtService {
        private String secretKey="";
        public JwtService(){
            try{
                KeyGenerator keyGen=KeyGenerator.getInstance("HmacSHA256");
                SecretKey key=keyGen.generateKey();
                secretKey=  Base64.getEncoder().encodeToString(key.getEncoded());

            }catch (NoSuchAlgorithmException e){
                throw new RuntimeException(e);
            }


        }

        public String generateToken(String username){
            Map<String,Object> claims=new HashMap<>();
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(username)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis()+60*60*30))
                    .signWith(getKey())
                    .compact();
        }
        private Key getKey() {
            byte[] keyBytes = Decoders.BASE64.decode(secretKey);
            return Keys.hmacShaKeyFor(keyBytes);

        }
        public String extractUserName(String token){
            return extractClaim(token, Claims::getSubject);
        }
        private <T> T extractClaim(String token, Function<Claims,T> claimResolver){
            final Claims claims=extractAllClaims(token);
            return claimResolver.apply(claims);
        }
        private Claims extractAllClaims(String token){
            return Jwts.parserBuilder()
                    .setSigningKey(getKey())
                    .build().parseClaimsJws(token).getBody();

        }
        public boolean validateToken(String token, UserDetails userDetails){
            final String userName=extractUserName(token);
            return(userName.equals(userDetails.getUsername())&& !isTokenExpired(token));
        }
        private Date extractExpiration(String token){
            return extractClaim(token, Claims::getExpiration);
        }
        private boolean isTokenExpired(String token){
            return extractExpiration(token).before(new Date());
        }
}