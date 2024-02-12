package com.airbnb.airbnb.auth.filter;

import com.airbnb.airbnb.auth.dto.LoginDto;
import com.airbnb.airbnb.member.entity.Member;
import com.airbnb.airbnb.auth.tokenizer.JwtTokenizer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
//UsernamePasswordAuthenticationFilter 상속 - 폼 로그인 방식에서 사용하는 디폴트 시큐리티 필터
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    //로그인 정보 전달받아 인증 여부 판단
    private final AuthenticationManager authenticationManager;
    //인증 성공시 jwt 생성 및 발급
    private final JwtTokenizer jwtTokenizer;

    @SneakyThrows
    @Override
    //인증 시도
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        //역직렬화를 위한 objectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        //클라이언트로부터 받은 request(username, password)를 loginDto 객체로 역직렬화
        LoginDto loginDto = objectMapper.readValue(request.getInputStream(), LoginDto.class);

        //Username, Password를 포함한 UsernamePasswordAuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
        //생성한 토큰을 authenticationManager에 전달하여 인증 처리 위임
        return authenticationManager.authenticate(authenticationToken);

    }
    @Override
    //인증 성공시 호출
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        //인증 성공시 인증된 authentication 객체가 생성되어 principal 필드에 member 객체가 할당됨
        //할당된 member 객체를 얻음
        Member member = (Member) authResult.getPrincipal();
        //얻은 객체로 access token 생성
        String accessToken = delegateAccessToken(member);
        //refresh token 생성
        String refreshToken = delegateRefreshToken(member);

        response.setHeader("Authorization", "Bearer " + accessToken);
        response.setHeader("Refresh", refreshToken);

        this.getSuccessHandler().onAuthenticationSuccess(request,response,authResult);

//        PrintWriter writer = response.getWriter();
//        writer.println("login success!");
//        writer.println("Authorization : " + "Bearer "+ accessToken);
//        writer.println("Refresh : " + refreshToken);
//        writer.flush();
    }

    //access token 생성
    private String delegateAccessToken(Member member) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", member.getEmail());
        claims.put("roles", member.getRoles());

        String subject = member.getEmail();
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
        String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);

        return accessToken;
    }

    //refresh token 생성
   private String delegateRefreshToken(Member member) {
        String subject = member.getEmail();
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getRefreshTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
        String refreshToken = jwtTokenizer.generateRefreshToken(subject, expiration, base64EncodedSecretKey);

        return refreshToken;
   }
}
