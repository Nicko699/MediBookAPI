package org.medibook.Dto.PatientDto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class PatientRegisterRequestDto {

    @Positive(message = "El DNI debe ser un número positivo")
    @NotNull(message = "Debe ingresar un DNI")
    @Size(min = 5, max = 20, message = "El DNI debe tener entre 5 y 20 caracteres")
    private String dni;
    @NotBlank(message = "Debe ingresar un género")
    private String gender;
    @Pattern(regexp = "\\d{10}", message = "El número de teléfono debe contener solo dígitos")
    @Size(min = 10, max = 10, message = "El número de teléfono debe tener 10 caracteres")
    @NotBlank(message = "Debe ingresar un número de teléfono")
    private String phoneNumber;
    @NotNull(message = "Debe ingresar una fecha de nacimiento")
    private LocalDate birthDate;

    public PatientRegisterRequestDto() {
    }

    public PatientRegisterRequestDto(String dni, String gender, String phoneNumber, LocalDate birthDate) {
        this.dni = dni;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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
}
