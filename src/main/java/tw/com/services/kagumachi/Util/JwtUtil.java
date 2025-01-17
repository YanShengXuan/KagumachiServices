package tw.com.services.kagumachi.Util;

import java.util.Date;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



public class JwtUtil {

    private static final String SECRET_KEY = "AAAAABNJSHGDGDGHSJSJJKAKAJSHHWUWIWKJSJHHDHDJDJDJJDJDJDJKS";
    private static final long EXPIRATION_TIME = 86400000; // 一天

    public static String generateToken(Long memberId) {
        return Jwts.builder()
                .setSubject(memberId.toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
