package org.medibook.Dto;

public class ResetTokenRequestDto {

    private String email;

    public ResetTokenRequestDto() {
    }

    public ResetTokenRequestDto(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
