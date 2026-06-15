package org.medibook.Dto.ResetTokenDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ResetPasswordRequestDto {

    @NotBlank(message = "El identificador del token es obligatorio")
    private String resetTokenId;

    @NotBlank(message = "El token es obligatorio")
    private String resetToken;

    @NotBlank(message = "La nueva contraseña es obligatoria")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String newPassword;

    public ResetPasswordRequestDto() {
    }

    public ResetPasswordRequestDto(String resetTokenId, String resetToken, String newPassword) {
        this.resetTokenId = resetTokenId;
        this.resetToken = resetToken;
        this.newPassword = newPassword;
    }

    public String getResetTokenId() {
        return resetTokenId;
    }

    public void setResetTokenId(String resetTokenId) {
        this.resetTokenId = resetTokenId;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
