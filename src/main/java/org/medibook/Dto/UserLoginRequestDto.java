package org.medibook.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserLoginRequestDto {
    @Email(message = "El correo electrónico debe de tener un formato válido")
    @NotBlank(message = "El correo electrónico es obligatorio")
    private String email;
    @Size(min = 8, max = 20,message = "La contraseña debe de tener entre 8 y 20 caractéres")
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    public UserLoginRequestDto() {
    }

    public UserLoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
