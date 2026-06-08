package org.medibook.Service;

import jakarta.servlet.http.HttpServletResponse;
import org.medibook.Dto.RefreshTokenRequestDto;
import org.medibook.Dto.RefreshTokenResponseDto;
import org.medibook.Exception.BadRequestException;
import org.medibook.Exception.NotFoundException;

public interface RefreshTokenService {

    public RefreshTokenResponseDto RefreshAccessToken(HttpServletResponse response, RefreshTokenRequestDto refreshTokenRequestDto) throws BadRequestException, NotFoundException;


}
