package com.mumu.novel.core.util;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

/**
 * @author mumu
 * @date 2022/10/10
 */
@Slf4j
@Component
@ConditionalOnProperty("novel.jwt.secret")
public class JwtUtils {

    /**
     * jwt 秘钥
     */
    @Value("${novel.jwt.secret}")
    private String secret;

    private static final String HEADER_SYSTEM_KEY = "systemKeyHeader";

    /**
     * 通过用户 id 生成 token
     */
    public String genToken(Long uid, String systemKey) {
        return Jwts.builder()
            .setHeaderParam(HEADER_SYSTEM_KEY, systemKey)
            .setSubject(uid.toString())
            .signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
            .compact();
    }

    public Long parseToken(String token, String systemKey) {
        Jws<Claims> claimsJws;
        try {
            claimsJws = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token);
            if (Objects.equals(claimsJws.getHeader().get(HEADER_SYSTEM_KEY), systemKey)) {
                return Long.parseLong(claimsJws.getBody().getSubject());
            }
        } catch (JwtException e) {
            log.warn("JWT 解析失败: {}", token);
        } 
        return null;
    }
    
    
}
