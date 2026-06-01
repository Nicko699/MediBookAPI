package org.medibook.Security;

import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.medibook.Security.TokenUtils.JwtTokenUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//Filtro personalizado de autenticación
@Service
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUserDetailsService userDetailsService;
    private final JwtTokenUtils jwtTokenUtils;

    public JwtAuthenticationFilter(JwtUserDetailsService userDetailsService, JwtTokenUtils jwtTokenUtils) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtils = jwtTokenUtils;
    }


    public String obtenerBearerToken(HttpServletRequest request){

        //Obtenemos el bearer token de la cabecera
        String bearerToken=request.getHeader("Authorization");

       //Validamos que el bearer token no venga vacio, null y que empiece con el prefijo bearer
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){

       //Retornamos el token desde la cadena número 7, omitiendo el bearer más un espacio
            return bearerToken.substring(7);
        }

        return null;
    }

    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request,@Nonnull HttpServletResponse response,@Nonnull FilterChain filterChain) throws ServletException, IOException {

        String token=obtenerBearerToken(request);

      //Validamos que el token no venga vacio o null, y validamos el token
        if (StringUtils.hasText(token) && jwtTokenUtils.validateToken(token)){

           // obtenemos el email del jwt
            String email=jwtTokenUtils.extractEmail(token);

           //Obtenemos los detalles del usuario en un userDetails
            UserDetails userDetails=userDetailsService.loadUserByUsername(email);

           //Creamos el objeto de autenticacion
            UsernamePasswordAuthenticationToken authentication=
                    new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

            //Guardamos ese objeto de autenticacion en el security context holder de spring para ser usado cuando queramos
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }
   //Le decimos a spring que finalice este filtro y pase a otro
        filterChain.doFilter(request,response);
    }
}
