package com.subproject.board.common.security;

import com.subproject.board.dto.auth.TokenDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class TokenProvider implements InitializingBean {
    private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);
    private final String secret;
    private final long accessTokenValidityInSeconds;
    private final long refreshTokenValidityInSeconds;
    private Key key;

    public TokenProvider(@Value("${jwt.secret}") String secret,
                         @Value("${jwt.access-token-validity-in-seconds}") long accessTokenValidityInSeconds,
                         @Value("${jwt.refresh-token-validity-in-seconds}") long refreshTokenValidityInSeconds) {

        this.secret = secret;
        this.accessTokenValidityInSeconds  = accessTokenValidityInSeconds;
        this.refreshTokenValidityInSeconds = refreshTokenValidityInSeconds;
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }
    public TokenDto createTokens(Authentication authentication){
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String userCode = userPrincipal.getUserCode();

        String accessToken = createToken(userCode, accessTokenValidityInSeconds);
        String refreshToken = createToken(userCode, refreshTokenValidityInSeconds);

        return TokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

    }
    public String createToken(String userCode, long validity){

        long now = (new Date()).getTime();

        return Jwts.builder()
                .setSubject(userCode)
                .setExpiration(new Date(now + validity))
                .setIssuedAt(new Date(now))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

    }
    public Authentication getAuthentication(String token){
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        UserPrincipal userPrincipal = new UserPrincipal(claims.getSubject(), null, null,null,null,null);

        return new UsernamePasswordAuthenticationToken(userPrincipal, token, null);
    }
    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e){
            logger.info("잘못된 JWT 서명입니다.");
        }catch (ExpiredJwtException e){
            logger.info("만료된 JWT 토큰입니다.");
        }catch (UnsupportedJwtException e){
            logger.info("지원되지 않는 JWT 토큰입니다.");
        }catch (IllegalArgumentException e){
            logger.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }

}
