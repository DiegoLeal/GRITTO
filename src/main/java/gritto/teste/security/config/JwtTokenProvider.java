package gritto.teste.security.config;

import gritto.teste.model.Usuario;
import gritto.teste.security.UserSecurity;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static gritto.teste.security.SecurityConstants.EXPIRATION_TIME;

@Component
public class JwtTokenProvider {

  @Value("${jwt.secret}")
  private String SECRET_KEY;

  @PostConstruct
  protected void init() {
    SECRET_KEY = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
  }

  public String generateToken(Authentication authentication) {
    Usuario usuario = (UserSecurity) authentication.getPrincipal();

    Date now = new Date(System.currentTimeMillis());
    Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

    String userId = Long.toString(usuario.getId());

    Map<String, Object> claims = new HashMap<>();
    claims.put("id", userId);
    claims.put("email", usuario.getEmail());
    claims.put("fullName", usuario.getFullName());

    return Jwts.builder()
            .setSubject(userId)
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
            .compact();
  }

  //Validate the token
  public boolean validateToken(String token) {
    try {
      Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
      return true;
    } catch (SignatureException ex) {
      System.out.println("Invalid JWT Signature");
    } catch (MalformedJwtException ex) {
      System.out.println("Invalid JWT Token");
    } catch (ExpiredJwtException ex) {
      System.out.println("Expired JWT token");
    } catch (UnsupportedJwtException ex) {
      System.out.println("Unsupported JWT token");
    } catch (IllegalArgumentException ex) {
      System.out.println("JWT claims string is empty");
    }
    return false;
  }


  //Get user Id from token
  public Long getUserIdFromJWT(String token) {
    Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    String id = (String) claims.get("id");
    return Long.parseLong(id);
  }
}
