package org.medibook.Dto.DoctorDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.medibook.Dto.SpecialityDto.SpecialityRequestDto;

public class DoctorRegisterRequestDto {

    @NotBlank(message = "El apellido es obligatorio")
    private String lastName;
    private String biography;
    private Integer yearsOfExperience;
    @NotBlank(message = "El número de teléfono es obligatorio")
    @Size(min = 10, max = 10, message = "El número de teléfono debe tener 10 caracteres")
    @Pattern(regexp = "\\d{10}", message = "El número de teléfono debe contener solo dígitos")
    private String phoneNumber;
    @Valid
    @NotNull(message = "Debe ingresar los datos de la especialidad")
    private SpecialityRequestDto speciality;

    public DoctorRegisterRequestDto() {
    }

    public DoctorRegisterRequestDto(String lastName, String biography, Integer yearsOfExperience, String phoneNumber, SpecialityRequestDto speciality) {
        this.lastName = lastName;
        this.biography = biography;
        this.yearsOfExperience = yearsOfExperience;
        this.phoneNumber = phoneNumber;
        this.speciality = speciality;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public SpecialityRequestDto getSpeciality() {
        return speciality;
    }

    public void setSpeciality(SpecialityRequestDto speciality) {
        this.speciality = speciality;
    }
}
