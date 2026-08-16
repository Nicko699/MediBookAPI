package org.medibook.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.medibook.Dto.ResetTokenDto.ForgotPasswordRequestDto;
import org.medibook.Dto.MessageResponseDto;
import org.medibook.Dto.ResetTokenDto.ResetPasswordRequestDto;
import org.medibook.Exception.BadRequestException;
import org.medibook.Exception.NotFoundException;
import org.medibook.Service.ResetToken.ResetTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/token")
@Tag(name = "Reset-token")
public class ResetTokenController {

    private final ResetTokenService resetTokenService;

    public ResetTokenController(ResetTokenService resetTokenService) {
        this.resetTokenService = resetTokenService;
    }

    @Operation(summary = "Process forgot password request")
    @PostMapping("/forgotPassword")
    public ResponseEntity<MessageResponseDto> processForgotPassword(@RequestBody @Valid ForgotPasswordRequestDto forgotPasswordRequestDto) throws NotFoundException{

        resetTokenService.processForgotPassword(forgotPasswordRequestDto);

        return ResponseEntity.ok(new MessageResponseDto("Si el correo existe, se enviará un enlace de recuperación."));
    }

    @Operation(summary = "Reset password using reset token")
    @PostMapping("/resetPassword")
    public ResponseEntity<MessageResponseDto> resetPassword(@RequestBody @Valid ResetPasswordRequestDto resetPasswordRequestDto) throws NotFoundException, BadRequestException{

        resetTokenService.resetPassword(resetPasswordRequestDto);

        return ResponseEntity.ok(new MessageResponseDto("La contraseña ha sido restablecida correctamente."));
    }
}
