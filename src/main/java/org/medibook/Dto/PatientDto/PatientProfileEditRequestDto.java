package org.medibook.Dto.PatientDto;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;

public class PatientProfileEditRequestDto {

    @Pattern(regexp = "^(?i)(MASCULINO|FEMENINO|OTRO)$", message = "El género debe ser MASCULINO, FEMENINO u OTRO")
    private String gender;

    @Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "El número de teléfono no es válido")
    private String phoneNumber;

    @Past(message = "La fecha de nacimiento debe ser una fecha pasada")
    private LocalDate birthDate;

    public PatientProfileEditRequestDto() {
    }

    public PatientProfileEditRequestDto(String gender, String phoneNumber, LocalDate birthDate) {
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
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