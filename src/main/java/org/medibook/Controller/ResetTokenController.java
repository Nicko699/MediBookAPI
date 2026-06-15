package org.medibook.Controller;

import jakarta.validation.Valid;
import org.medibook.Dto.ResetTokenDto.ForgotPasswordRequestDto;
import org.medibook.Dto.MessageResponseDto;
import org.medibook.Dto.ResetTokenDto.ResetPasswordRequestDto;
import org.medibook.Exception.BadRequestException;
import org.medibook.Exception.NotFoundException;
import org.medibook.Service.ResetTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/token")
public class ResetTokenController {

    private final ResetTokenService resetTokenService;

    public ResetTokenController(ResetTokenService resetTokenService) {
        this.resetTokenService = resetTokenService;
    }


    @PostMapping("/forgotPassword")
    public ResponseEntity<MessageResponseDto> processForgotPassword(@RequestBody @Valid ForgotPasswordRequestDto forgotPasswordRequestDto) throws NotFoundException{

        resetTokenService.processForgotPassword(forgotPasswordRequestDto);

        return ResponseEntity.ok(new MessageResponseDto("Si el correo existe, se enviará un enlace de recuperación."));
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<MessageResponseDto> resetPassword(@RequestBody @Valid ResetPasswordRequestDto resetPasswordRequestDto) throws NotFoundException, BadRequestException{

        resetTokenService.resetPassword(resetPasswordRequestDto);

        return ResponseEntity.ok(new MessageResponseDto("La contraseña ha sido restablecida correctamente."));
    }
}
