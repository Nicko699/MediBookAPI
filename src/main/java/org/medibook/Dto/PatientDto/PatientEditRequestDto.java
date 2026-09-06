package org.medibook.Dto.PatientDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.medibook.Dto.UserDto.UserEditRequestDto;

import java.time.LocalDate;

public class PatientEditRequestDto {
    @Valid
    private UserEditRequestDto user;
    @Size(min = 5, max = 20, message = "El DNI debe tener entre 5 y 20 caracteres")
    private String dni;
    @Pattern(regexp = "^(?i)(MASCULINO|FEMENINO|OTRO)$", message = "El género debe ser MASCULINO, FEMENINO u OTRO")
    private String gender;
    @Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "El número de teléfono no es válido")
    private String phoneNumber;
    @Past(message = "La fecha de nacimiento debe ser una fecha pasada")
    private LocalDate birthDate;

    public PatientEditRequestDto() {
    }

    public PatientEditRequestDto(UserEditRequestDto user, String dni, String gender, String phoneNumber, LocalDate birthDate) {
        this.user = user;
        this.dni = dni;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
    }

    public UserEditRequestDto getUser() {
        return user;
    }

    public void setUser(UserEditRequestDto user) {
        this.user = user;
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
