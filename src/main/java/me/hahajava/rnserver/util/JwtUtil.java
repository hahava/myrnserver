package me.hahajava.rnserver.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.sql.Date;
import java.time.LocalDate;

public final class JwtUtil {

    public static final String HEADER_KEY = "heeman";
    public static final String SECRET_KEY = "secret";
    public static final String TOKEN_PREFIX = "auth";

    private JwtUtil() {
    }

    public static String decodeJwtToString(String token) {
        return JWT
                .require(Algorithm.HMAC512(SECRET_KEY))
                .build()
                .verify(token.replace(TOKEN_PREFIX, ""))
                .getSubject();
    }

    public static String encodeStringToJwt(String value, int days) {
        return JWT
                .create()
                .withSubject(value)
                .withExpiresAt(Date.valueOf(LocalDate.now().plusDays(days)))
                .sign(Algorithm.HMAC512(SECRET_KEY));
    }

    public static String encodeStringToJwt(String value) {
        return JWT
                .create()
                .withSubject(value)
                .withExpiresAt(Date.valueOf(LocalDate.now().plusDays(2)))
                .sign(Algorithm.HMAC512(SECRET_KEY));
    }


}
