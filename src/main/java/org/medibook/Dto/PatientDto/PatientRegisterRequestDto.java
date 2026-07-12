package org.medibook.Dto.PatientDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.medibook.Dto.UserDto.UserRegisterPatientRequestDto;
import org.medibook.Dto.UserDto.UserRegisterRequestDto;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.time.LocalDate;

public class PatientRegisterRequestDto {

    @NotBlank(message = "Debe ingresar un apellido")
    private String lastName;
    @Positive(message = "El DNI debe ser un número positivo")
    @NotNull(message = "Debe ingresar un DNI")
    private Long dni;
    @Pattern(regexp = "\\d{10}", message = "El número de teléfono debe contener solo dígitos")
    @Size(min = 10, max = 10, message = "El número de teléfono debe tener 10 caracteres")
    @NotBlank(message = "Debe ingresar un número de teléfono")
    private String phoneNumber;
    @NotNull(message = "Debe ingresar una fecha de nacimiento")
    private LocalDate birthDate;
    @Valid
    @NotNull(message = "Debe ingresar los datos del usuario")
    private UserRegisterPatientRequestDto userRegister;

    public PatientRegisterRequestDto() {
    }

    public PatientRegisterRequestDto(String lastName, Long dni, String phoneNumber, LocalDate birthDate, UserRegisterPatientRequestDto userRegister) {
        this.lastName = lastName;
        this.dni = dni;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.userRegister = userRegister;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public UserRegisterPatientRequestDto getUserRegister() {
        return userRegister;
    }

    public void setUserRegister(UserRegisterPatientRequestDto userRegister) {
        this.userRegister = userRegister;
    }
}
