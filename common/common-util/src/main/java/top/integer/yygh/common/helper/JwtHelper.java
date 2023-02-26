package top.integer.yygh.common.helper;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

public class JwtHelper {
    private static final String SALT = "9gH%bYyfPr1zEP&ygS0fTV7j*ZcfCcW";
    private static final JWTVerifier VERIFIER = JWT.require(Algorithm.HMAC512(SALT)).build();
    public static String createToken(Long id, String username) {
        return JWT.create()
                .withSubject("yygh")
                .withClaim("userId", id)
                .withClaim("username", username)
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 24))
                .sign(Algorithm.HMAC512(SALT));

    }

    public static Long getUserId(String token) {
        return JWT.decode(token).getClaim("userId").asLong();
    }

    public static String getUsername(String token) {
        return JWT.decode(token).getClaim("username").asString();
    }

    public static void verify(String token) {
        VERIFIER.verify(token);
    }

}
