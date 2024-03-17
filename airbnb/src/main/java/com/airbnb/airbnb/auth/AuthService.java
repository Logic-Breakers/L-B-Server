package com.airbnb.airbnb.auth;

import com.airbnb.airbnb.auth.dto.LoginDto;
import com.airbnb.airbnb.auth.dto.TokenDto;
import com.airbnb.airbnb.redis.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {
    private final RedisUtil redisUtil;

    public String logout(String accessToken) {
        redisUtil.setBlackList(accessToken, "accessToken", 5);
        return "로그아웃 완료";
    }

    public ResponseEntity login(HttpServletResponse response) {
        TokenDto tokenDto = new TokenDto();
        tokenDto.setAccessToken(response.getHeader("Authorization").replace("Bearer ", ""));
        log.info("message",response.getHeader("Authorization").replace("Bearer ", ""));
        tokenDto.setRefreshToken(response.getHeader("refresh"));
        return new ResponseEntity<>(tokenDto, HttpStatus.OK);
    }
}
