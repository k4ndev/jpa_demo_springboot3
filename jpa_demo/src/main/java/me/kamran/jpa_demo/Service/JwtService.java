package me.kamran.jpa_demo.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static String Secret_key="18E96D4EA77AE6A73B8BAD9D4FBE75156HR4T45GD8R46FD6F4FWF8964FWE98F4F4EW8F4EW4F9FE49WEF";

    private Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(getSignInKey()).parseClaimsJws(token).getBody();
    }

    private Key getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(Secret_key);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public <T> T extractClaims(String token, Function<Claims,T> claimsres){
        final Claims claims = extractAllClaims(token);
        return  claimsres.apply(claims);
    }

    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaims(token,Claims::getExpiration);
    }

    public String extractUserName(String token){
        return extractClaims(token,Claims::getSubject);
    }

    public boolean validateToken(String token,UserDetails userDetails){
        final String username = extractClaims(token,Claims::getSubject);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }
    public String generateToken(Map<String, Object> claims, UserDetails userDetails){

        return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getSignInKey(),SignatureAlgorithm.HS256).compact();
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }

    private String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*36000))
                .signWith(SignatureAlgorithm.HS256,Secret_key).compact();
    }
}
