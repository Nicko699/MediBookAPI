package org.medibook.Controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.medibook.Dto.RefreshTokenDto.RefreshTokenRequestDto;
import org.medibook.Dto.RefreshTokenDto.RefreshTokenResponseDto;
import org.medibook.Exception.BadRequestException;
import org.medibook.Exception.NotFoundException;
import org.medibook.Service.RefreshToken.RefreshTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/token")
public class RefreshTokenController {

    private final RefreshTokenService refreshTokenService;

    public RefreshTokenController(RefreshTokenService refreshTokenService) {
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/refreshAccessToken")
    public ResponseEntity<RefreshTokenResponseDto> RefreshAccessToken(HttpServletResponse response,@RequestBody @Valid RefreshTokenRequestDto refreshTokenRequestDto) throws BadRequestException, NotFoundException{

        RefreshTokenResponseDto refreshTokenResponseDto=refreshTokenService.RefreshAccessToken(response, refreshTokenRequestDto);

        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(refreshTokenResponseDto);

    }

}
