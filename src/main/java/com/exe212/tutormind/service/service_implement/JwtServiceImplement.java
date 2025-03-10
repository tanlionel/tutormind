package com.exe212.tutormind.service.service_implement;

import com.exe212.tutormind.entity.User;
import com.exe212.tutormind.exception.InvalidateException;
import com.exe212.tutormind.exception.TokenExpiredException;
import com.exe212.tutormind.exception.UserDoesNotExistException;
import com.exe212.tutormind.service.service_interface.JwtService;
import com.exe212.tutormind.service.service_interface.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtServiceImplement implements JwtService {
    @Autowired
    private final UserService userService;

    private static final String SECRET_KEY = "ownrJE4LNVXTBOUdVZ2xmJ7VSDNhKTRJsagLsdS3jLfsOY91basfKf";

    @Override
    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
    }

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    @Override
    public String generateToken(Map<String, Object> extraClaims, User user) { // generate token with claims (access token)
        String token = Jwts.builder().setClaims(extraClaims)
                .setSubject(user.getUsername())
                .claim("email",user.getEmail())
                .claim("userId", user.getId())
                .claim("RoleName", user.getRole().getName())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();


        return token;
    }

    @Override
    public String generateAccessToken(User user) {
        return generateToken(new HashMap<>(), user);
    }

    @Override
    public String generateRefreshToken(User user) {
        String jwt = Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(user.getUsername()) // Thêm subject
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
        return jwt;
    }

    private Key getSignInKey() {
        try {
            byte[] decodedKey = Decoders.BASE64.decode(SECRET_KEY);
            return Keys.hmacShaKeyFor(decodedKey);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error decoding secret key", e);
        }
    }

    @Override
    public boolean isValidToken(String token, UserDetails user) {
        try {
            Claims claims = extractAllClaims(token);
            String usernameFromToken = claims.getSubject();
            return (usernameFromToken.equals(user.getUsername()) && !isTokenExpired(token));
        } catch (Exception e) {
            // Logging lỗi nếu có vấn đề khi kiểm tra
            e.printStackTrace();
            return false;
        }
    }


    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    @Override
    public String generateAccessToken(String refreshToken) throws TokenExpiredException, UserDoesNotExistException, InvalidateException {
        String username = extractUsername(refreshToken);
        User user = userService.getUserByUserName(username);

        if (isValidToken(refreshToken, user)) {
            return generateAccessToken(user);
        } else {
            throw new TokenExpiredException();
        }
    }

    public void detachToken(String token) throws UserDoesNotExistException, InvalidateException {
        String email = extractUsername(token);
    }


}
