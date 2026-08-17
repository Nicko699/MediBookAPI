package org.medibook.Service.RefreshToken;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.medibook.Dto.RefreshTokenDto.RefreshTokenRequestDto;
import org.medibook.Dto.RefreshTokenDto.RefreshTokenResponseDto;
import org.medibook.Exception.BadRequestException;
import org.medibook.Exception.NotFoundException;

public interface RefreshTokenService {

    public RefreshTokenResponseDto RefreshAccessToken(HttpServletRequest request, HttpServletResponse response) throws BadRequestException, NotFoundException;


}
