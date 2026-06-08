package org.medibook.Dto;

public class ResetTokenDto {

    private String resetTokenId;
    private String resetToken;

    public ResetTokenDto() {
    }

    public ResetTokenDto(String resetTokenId, String resetToken) {
        this.resetTokenId = resetTokenId;
        this.resetToken = resetToken;
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
}
