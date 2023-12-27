package me.kamran.jpa_demo.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static String Secret_key="secret_key";

    private Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(Secret_key).parseClaimsJws(token).getBody();
    }

    public <T> T extractClaims(String token, Function<Claims,T> claimsres){
        final Claims claims = extractAllClaims(token);
        return  claimsres.apply(claims);
    }

    public Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaims(token,Claims::getExpiration);
    }

    public String extractUserName(String token){
        return extractClaims(token,Claims::getSubject);
    }

    public Boolean validateToken(String token){
        final String username = extractClaims(token,Claims::getSubject);
        return username=="kamran" && isTokenExpired(token);
    }
    public String generateToken(String username){
        Map<String,Object> claims = new HashMap<>();
        return createToken(claims,username);
    }

    private String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*36000))
                .signWith(SignatureAlgorithm.HS512,Secret_key).compact();
    }
}
