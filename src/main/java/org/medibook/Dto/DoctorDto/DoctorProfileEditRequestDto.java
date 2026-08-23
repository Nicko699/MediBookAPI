package org.medibook.Dto.DoctorDto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class DoctorProfileEditRequestDto {

    @Size(max = 1000, message = "La biografía no puede exceder los 1000 caracteres")
    private String biography;

    @Min(value = 0, message = "Los años de experiencia no pueden ser negativos")
    @Max(value = 70, message = "Los años de experiencia no son válidos")
    private Integer yearsOfExperience;

    @Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "El número de teléfono no es válido")
    private String phoneNumber;

    public DoctorProfileEditRequestDto() {
    }

    public DoctorProfileEditRequestDto(String biography, Integer yearsOfExperience, String phoneNumber) {
        this.biography = biography;
        this.yearsOfExperience = yearsOfExperience;
        this.phoneNumber = phoneNumber;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}