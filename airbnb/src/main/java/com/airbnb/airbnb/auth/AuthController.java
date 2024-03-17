package com.airbnb.airbnb.auth;

import com.airbnb.airbnb.auth.dto.LoginDto;
import com.airbnb.airbnb.auth.dto.TokenDto;
import com.airbnb.airbnb.member.entity.Member;
import com.airbnb.airbnb.redis.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class AuthController {
    private final RedisUtil redisUtil;
    private final AuthService authService;

    @GetMapping("/auth/login")
    public ResponseEntity login(HttpServletResponse response) {
        return ResponseEntity.ok(authService.login(response));
    }

    @DeleteMapping("/auth/logout")
    public ResponseEntity<String> logout(@AuthenticationPrincipal Member member,
                                         @RequestBody TokenDto tokenDto) {
        return ResponseEntity.ok(authService.logout(tokenDto.getAccessToken()));
    }
}
