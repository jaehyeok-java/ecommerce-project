package com.project.domain.config;

import com.project.domain.domain.common.UserType;
import com.project.domain.domain.common.UserVo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtAuthenticationProvider {
    private static final long TOKEN_VALID_TIME = 1000L * 60 * 60 * 24; // 24시간

    private final SecretKey signingKey;

    public JwtAuthenticationProvider(@Value("${jwt.secret}") String secretKey) {
        this.signingKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    // JWT 토큰 생성
    public String createToken(String email, Long id, UserType userType) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(email) // Subject 에 이메일 저장
                .claim("id", id)
                .claim("roles", userType.name())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + TOKEN_VALID_TIME))
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
    }

    // JWT 토큰 검증
    public boolean validateToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(signingKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return !claims.getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // JWT 토큰에서 사용자 정보 추출
    public UserVo getUserVo(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Long id = claims.get("id", Long.class);
        String email = claims.getSubject();
        return new UserVo(id, email);
    }
}
