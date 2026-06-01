package org.medibook.Security;

import jakarta.annotation.Nonnull;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

//Clase de configuración para respuestas 401 cuando el usuario no puede ingresar al sistema
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(@Nonnull HttpServletRequest request,@Nonnull HttpServletResponse response,@Nonnull AuthenticationException authException) throws IOException, ServletException {
       //Indicamos que la respuesta es 401_UNAUTHORIZED, va a ser de tipo Json
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        Map<String,Object>message=new HashMap<>();
        //Armamos la estructura del mensaje
        message.put("typeError",401);
        message.put("status",HttpServletResponse.SC_UNAUTHORIZED);
        message.put("message",authException.getLocalizedMessage());
        message.put("date", Instant.now());

        String json=new ObjectMapper().writeValueAsString(message);

        response.getWriter().write(json);

    }
}
