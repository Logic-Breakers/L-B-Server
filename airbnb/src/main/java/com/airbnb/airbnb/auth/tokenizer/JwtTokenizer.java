package com.airbnb.airbnb.auth.tokenizer;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Component
public class JwtTokenizer {

    @Getter
    @Value("${jwt.key}")
    private String secretKey;

    @Getter
    @Value("${jwt.access-token-expiration-minutes}")
    private int accessTokenExpirationMinutes;

    @Getter
    @Value("${jwt.refresh-token-expiration-minutes}")
    private int refreshTokenExpirationMinutes;

    // plain text 형태의 secretkey base64형식의 문자열로 인코딩
    public String encodeBase64SecretKey(String secretKey) {
        return Encoders.BASE64.encode(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    // JWT의 서명에 사용할 Secret Key 생성
    private Key makeKeyFromBase64EncodedKey(String base64EncodedSecretKey) {
        //Base64로 인코딩된 SecretKey 디코딩
        byte[] keyBytes = Decoders.BASE64.decode(base64EncodedSecretKey);
        //적절한 HMAC 알고리즘을 적용한 Key 생성
        Key key = Keys.hmacShaKeyFor(keyBytes);
        return key;
    }

    //인증된 사용자에게 최초로 JWT 발급
    public String generateAccessToken(Map<String, Object> claims,
                                      String subject,
                                      Date expiration,
                                      String basse64EncodedSecretKey) {

        //Key 객체 얻음
        Key key = makeKeyFromBase64EncodedKey(basse64EncodedSecretKey);

        return Jwts.builder()
                .setClaims(claims) //Jwt에 포함시킬 Custom Claims 추가
                .setSubject(subject) //Jwt 제목 추가
                .setIssuedAt(Calendar.getInstance().getTime()) //Jwt 발행일자 설정
                .setExpiration(expiration) //Jwt 만료 일자
                .signWith(key) //서명을 위한 Key 객체 설정
                .compact(); //Jwt 생성하고 문자열 형태로 직렬화
    }

    //리프레쉬 토큰 생성
    public String generateRefreshToken(String subject, Date expiration, String base64EncodedSecretKey) {
        Key key = makeKeyFromBase64EncodedKey(base64EncodedSecretKey);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(Calendar.getInstance().getTime())
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }

    //JWT의 Signature을 검증하여 위변조 확인
    public void verifySignature(String jws, String base64EncodedSecretKey) {
        Key key = makeKeyFromBase64EncodedKey(base64EncodedSecretKey);

        Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jws);
    }

    public Jws<Claims> getClaims(String jws, String base64EncodedSecretKey) {
        Key key = makeKeyFromBase64EncodedKey(base64EncodedSecretKey);

        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jws);
        return claims;
    }

    //JWT 만료 일시 지정 메서드
    public Date getTokenExpiration(int expirationMinutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, expirationMinutes);
        Date expiration = calendar.getTime();

        return expiration;
    }
}
