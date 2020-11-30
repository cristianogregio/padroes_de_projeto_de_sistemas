package gov.br.sp.fatec.condominio.security;

import java.io.IOException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JsonParseException;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import gov.br.sp.fatec.condominio.entities.Usuario;
import io.jsonwebtoken.*;

/**
 * @author Cristiano
 */
@Component
public class JwtUtils
{

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    
    private static final String SECRET_KEY = "18a518d7-2681-45e3-beed-88f12d8932df";
    
    @Value("${condominio.jwtSecret}")
    private String jwtSecret;

    @Value("${condominio.jwtExpirationMs}")
    private int jwtExpirationMs;
    
    public String generateToken(Usuario usuario) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Login login = new Login();
        //login.setUsername(usuario.getName());
        if (!usuario.getAuthorities().isEmpty())
        {
            login.setAutorizacao(usuario.getAuthorities().iterator().next().getAuthority());
        }
        String usuarioJson = mapper.writeValueAsString(usuario);
        Date agora = new Date();
        Long hora = 1000L * 60L * 60L; // Uma hora
        return Jwts.builder().claim("userDetails", usuarioJson)
            .setIssuer("br.gov.sp.fatec")
            .setSubject(usuario.getNome())
            .setExpiration(new Date(agora.getTime() + hora))
            .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
            .compact();
    }
    
    public static Usuario parseToken(String token) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        String credentialsJson = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .get("userDetails", String.class);
        return mapper.readValue(credentialsJson, Usuario.class);
    }
    
    public String generateJwtToken(Authentication authentication) {

        Usuario userPrincipal = (Usuario) authentication.getPrincipal();

        return Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}
