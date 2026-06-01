package org.medibook.Security.TokenUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.medibook.Security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;

//Clase para crear el token de acceso, validar y obtener el usuario del token
@Service
public class JwtTokenUtils {

    //Traemos la firma de 32 digitos de las variables de entorno
    @Value("${spring.security.jwt.token.signature}")
    private String signature;

    private SecretKey key;

    //Le decimos que se ejecute una vez se hayan inyectado todos los valores, en este caso value de las variables de entorno
    //y construimos la firma dentro del entorno
    @PostConstruct
    public void init(){

        key= Keys.hmacShaKeyFor(signature.getBytes(StandardCharsets.UTF_8));

    }

    public String token(Authentication authentication) {

        //Pasamos las autoridades de GrantedAuthority a una lista
        List<String> rolesList = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        //Obtenemos la información dle usuario en authentication
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        //Si viene null lanzamos una excepcion
        if (customUserDetails == null) {
            throw new UsernameNotFoundException("No se encontró la información del usuario en el objeto de authentication");
        }

        //Retornamos, email, nombre del usuario y los roles
        return createAccessToken(authentication.getName(), customUserDetails.getName(), rolesList);
    }

    public String createAccessToken(String email, String nameUser, List<String>rolesList){

        return Jwts.builder()
                .subject(email)
                .claim("name",nameUser)
                .claim("roles",rolesList)
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plus(Duration.ofMinutes(15))))
                .signWith(key)
                .compact();
    }

    //metodo para extraer el email del jwt
    public String extractEmail(String token){

        Claims claims=Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }

    //metodo para validar el jet
    public Boolean validateToken(String token){

        try {
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
            return true;
        }
        catch (JwtException e){
            return false;
        }
    }

}
