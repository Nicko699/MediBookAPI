package org.medibook.Dto;

public class RefreshTokenDto {

    private String refreshTokenId;
    private String refreshToken;

    public RefreshTokenDto() {
    }

    public RefreshTokenDto(String refreshTokenId, String refreshToken) {
        this.refreshTokenId = refreshTokenId;
        this.refreshToken = refreshToken;
    }

    public String getRefreshTokenId() {
        return refreshTokenId;
    }

    public void setRefreshTokenId(String refreshTokenId) {
        this.refreshTokenId = refreshTokenId;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
