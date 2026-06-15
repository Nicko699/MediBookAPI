package org.medibook.Security;

import jakarta.annotation.Nonnull;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
//Clase de configuración para respuestas 403 cuando el usuario no tenga permisos para ingresar algún recurso
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(@Nonnull HttpServletRequest request,@Nonnull HttpServletResponse response,@Nonnull AccessDeniedException accessDeniedException) throws IOException, ServletException {
        //Indicamos que la respuesta es 403_FORBIDDEN, va a ser de tipo Json
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        Map<String,Object>message=new HashMap<>();

        //Armamos la estructura del mensaje
        message.put("typeError","Forbidden");
        message.put("status",HttpServletResponse.SC_FORBIDDEN);
        message.put("message",accessDeniedException.getLocalizedMessage());
        message.put("date", Instant.now());

        String json=new ObjectMapper().writeValueAsString(message);

        response.getWriter().write(json);
    }
}
