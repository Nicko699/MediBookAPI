package org.medibook.Service.ResetToken;

import org.medibook.Dto.ResetTokenDto.ForgotPasswordRequestDto;
import org.medibook.Dto.ResetTokenDto.ResetPasswordRequestDto;
import org.medibook.Exception.BadRequestException;
import org.medibook.Exception.NotFoundException;

public interface ResetTokenService {

    public void processForgotPassword(ForgotPasswordRequestDto forgotPasswordRequestDto) throws NotFoundException;

    public void resetPassword(ResetPasswordRequestDto resetPasswordRequestDto) throws NotFoundException, BadRequestException;
}
