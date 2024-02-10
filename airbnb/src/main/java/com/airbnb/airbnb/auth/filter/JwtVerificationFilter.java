package com.airbnb.airbnb.auth.filter;

import com.airbnb.airbnb.auth.tokenizer.JwtTokenizer;
import com.airbnb.airbnb.auth.userdetails.MemberDetailsService;
import com.airbnb.airbnb.auth.utils.CustomAuthorityUtils;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
//request당 한 번만 실행되는 filter 구현(jwt 검증은 1회만 수행하면 되므로)
public class JwtVerificationFilter extends OncePerRequestFilter {
    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils customAuthorityUtils;
    private final MemberDetailsService memberDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            Map<String, Object> claims = verifyJws(request);  //jwt 검증
            setAuthenticationToContext(claims); //Authentication 객체를 SecurityContext에 저장
        } catch (ExpiredJwtException ee) {
            request.setAttribute("exception", ee);
        } catch (Exception e) {
            request.setAttribute("exception", e);
        }
        filterChain.doFilter(request, response);    //다음 filter 호출
    }

    @Override
    //특정 조건에 true일 때 해당 필터 수헹하지 않고 다음 필터로 건너뛰기
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String authorization = request.getHeader("Authorization");
        //authorization header가 null이거나 Bearer로 시작하지 않을 때
        //즉, JWT가 포함되어 있지 않으면 JWT 검증이 필요하지 않다고 판단, 다음 필터로 넘김
        return authorization == null || !authorization.startsWith("Bearer");
    }

    //클라이언트가 보낸 JWT 검증
    private Map<String,Object> verifyJws(HttpServletRequest request) {
        String jws = request.getHeader("Authorization").replace("Bearer ", ""); //Bearer 부분 제거
        //JWT signature를 검증하기 위한 secret key
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
        //JWT에서 claims 파싱 <- claims가 파싱 가능하다는 건 signature 검증에 성공했다는 것
        Map<String, Object> claims = jwtTokenizer.getClaims(jws, base64EncodedSecretKey).getBody();

        return claims;
    }

    //Authentication 객체를 SecurityContext에 저장
    private void setAuthenticationToContext(Map<String, Object> claims) {
        UserDetails userDetails = memberDetailsService.loadUserByUsername((String) claims.get("username"));
        //claims를 기반으로 List<GrantedAuthority> 생성
        List<GrantedAuthority> authorities = customAuthorityUtils.createAuthorities((List)claims.get("roles"));
        //username과 List<GrantedAuthority>를 포함한 authentication 객체 생성
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
        //securityContext에 저장
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
