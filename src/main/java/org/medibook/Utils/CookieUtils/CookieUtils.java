package org.medibook.Utils.CookieUtils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Duration;

@Service
public class CookieUtils {

    public void createCookieOnly(HttpServletResponse response, String refreshTokenId, String refreshToken) {

        if (StringUtils.hasText(refreshTokenId)) {

            ResponseCookie responseCookie1 = ResponseCookie.from("refreshTokenId", refreshTokenId)
                    .httpOnly(true)//hacemos que la cookie no pueda ser leida por javaScript
                    .secure(false)//Indicamos que se enviara solo si es Https cuando es true, falta para pruebas http
                    .sameSite("Lax")
                    .path("/")//Indicamos que cuando el metodo con esta ruta se ejecute, traiga la cookie
                    .maxAge(Duration.ofDays(1))//Indicamos que se expire después de 1 día
                    .build();

            response.addHeader(HttpHeaders.SET_COOKIE,responseCookie1.toString());
        }

        if (StringUtils.hasText(refreshToken)){

            ResponseCookie responseCookie2= ResponseCookie.from("refreshToken",refreshToken)
                    .httpOnly(true)//hacemos que la cookie no pueda ser leida por javaScript
                    .secure(false)//Indicamos que se enviara solo si es Https cuando es true, falta para pruebas http
                    .sameSite("Lax")
                    .path("/")//Indicamos que cuando el metodo con esta ruta se ejecute, traiga la cookie
                    .maxAge(Duration.ofDays(1))//Indicamos que se expire después de 1 día
                    .build();

            response.addHeader(HttpHeaders.SET_COOKIE,responseCookie2.toString());
        }
}

    public String getCookieValue(HttpServletRequest request, String cookieName) {

        if (request.getCookies() == null) {
            return null;
        }

        for (Cookie cookie : request.getCookies()) {

            if (cookie.getName().equals(cookieName)) {
                return cookie.getValue();
            }
        }

        return null;
    }}
