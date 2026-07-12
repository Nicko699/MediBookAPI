package org.medibook.Dto.UserDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRegisterUserRequestDto {

    @NotBlank(message = "Debe ingresar un nombre")
    private String name;
    @Email(message = "El correo electrónico debe estar en un formato válido")
    @NotBlank(message = "Debe ingresar un correo electrónico")
    private String email;
    @Size(min = 8, max = 20, message = "La contraseña debe tener entre 8 a 20 caracteres")
    @NotBlank(message = "Debe ingresar una contraseña")
    private String password;

    public UserRegisterUserRequestDto() {
    }

    public UserRegisterUserRequestDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
