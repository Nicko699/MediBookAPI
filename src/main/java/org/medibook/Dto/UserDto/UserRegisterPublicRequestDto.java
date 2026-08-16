package org.medibook.Dto.UserDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.medibook.Dto.PatientDto.PatientRegisterRequestDto;

public class UserRegisterPublicRequestDto {

    @NotBlank(message = "Debe ingresar un nombre")
    private String name;
    @NotBlank(message = "Debe ingresar un apellido")
    private String lastName;
    @Email(message = "El correo electrónico debe estar en un formato válido")
    @NotBlank(message = "Debe ingresar un correo electrónico")
    private String email;
    @Size(min = 8, max = 20, message = "La contraseña debe tener entre 8 a 20 caracteres")
    @NotBlank(message = "Debe ingresar una contraseña")
    private String password;
    @Valid
    @NotNull(message = "Debe ingresar los datos del paciente")
    PatientRegisterRequestDto patient;


    public UserRegisterPublicRequestDto() {
    }

    public UserRegisterPublicRequestDto(String name, String lastName, String email, String password, PatientRegisterRequestDto patient) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.patient = patient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public PatientRegisterRequestDto getPatient() {
        return patient;
    }

    public void setPatient(PatientRegisterRequestDto patient) {
        this.patient = patient;
    }
}
