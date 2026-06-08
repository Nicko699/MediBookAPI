package org.medibook.Service;

import jakarta.servlet.http.HttpServletResponse;
import org.medibook.Dto.RefreshTokenDto;
import org.medibook.Dto.RefreshTokenRequestDto;
import org.medibook.Dto.RefreshTokenResponseDto;
import org.medibook.Exception.BadRequestException;
import org.medibook.Exception.NotFoundException;
import org.medibook.Model.RefreshToken;
import org.medibook.Model.Rol;
import org.medibook.Model.User;
import org.medibook.Repository.RefreshTokenRepository;
import org.medibook.Security.CookieUtils.CookieUtils;
import org.medibook.Security.TokenUtils.JwtTokenUtils;
import org.medibook.Security.TokenUtils.RefreshTokenUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtils jwtTokenUtils;
    private final RefreshTokenUtils refreshTokenUtils;
    private final CookieUtils cookieUtils;


    public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository, PasswordEncoder passwordEncoder, JwtTokenUtils jwtTokenUtils, RefreshTokenUtils refreshTokenUtils, CookieUtils cookieUtils) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtils = jwtTokenUtils;
        this.refreshTokenUtils = refreshTokenUtils;
        this.cookieUtils = cookieUtils;
    }

    @Transactional
    @Override
    public RefreshTokenResponseDto RefreshAccessToken(HttpServletResponse response, RefreshTokenRequestDto refreshTokenRequestDto) throws BadRequestException, NotFoundException {

        RefreshToken refreshToken=refreshTokenRepository
                .findByRefreshTokenIdAndActiveTrue(refreshTokenRequestDto.getRefreshTokenId(),true)
                .orElseThrow(()->new NotFoundException("El refresh token con el Id: "+refreshTokenRequestDto
                        .getRefreshTokenId()+" está inactivo o no se encuentra en el sistema"));

    boolean  tokenMatches=passwordEncoder
            .matches(refreshTokenRequestDto
                    .getRefreshToken(),refreshToken.getRefreshTokenEncript());

    boolean tokenExpired=refreshToken.getExpiredAt().isBefore(Instant.now());

        if (!tokenMatches || tokenExpired){

            throw new BadRequestException("RefreshToken inválido o ha expirado");

        }

       User user=refreshToken.getUser();

        List<String> roles=user.getListRoles()
                .stream()
                .map(Rol::getName)
                .toList();

        String accessToken= jwtTokenUtils.createAccessToken(user.getEmail(),user.getName(),roles);

        RefreshTokenDto newRefreshToken=refreshTokenUtils.createRefreshToken(user.getEmail());

        cookieUtils.createCookieOnly(response,newRefreshToken.getRefreshTokenId(),newRefreshToken.getRefreshToken());

        return new RefreshTokenResponseDto(accessToken,"Bearer ");
    }
}
