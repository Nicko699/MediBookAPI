package org.medibook.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.medibook.Dto.RefreshTokenDto.RefreshTokenResponseDto;
import org.medibook.Exception.BadRequestException;
import org.medibook.Exception.NotFoundException;
import org.medibook.Service.RefreshToken.RefreshTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/token")
@Tag(name = "Refresh-token")
public class RefreshTokenController {

    private final RefreshTokenService refreshTokenService;

    public RefreshTokenController(RefreshTokenService refreshTokenService) {
        this.refreshTokenService = refreshTokenService;
    }

    @Operation(summary = "Refresh access token")
    @PostMapping("/refreshAccessToken")
    public ResponseEntity<RefreshTokenResponseDto> RefreshAccessToken (HttpServletRequest request, HttpServletResponse response) throws BadRequestException, NotFoundException{

        RefreshTokenResponseDto refreshTokenResponseDto=refreshTokenService.RefreshAccessToken(request, response);

        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(refreshTokenResponseDto);

    }

}
