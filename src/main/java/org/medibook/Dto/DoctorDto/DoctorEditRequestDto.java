package org.medibook.Dto.DoctorDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.medibook.Dto.SpecialityDto.SpecialityRequestDto;
import org.medibook.Dto.UserDto.UserEditDataRequestDto;


public class DoctorEditRequestDto {
    @Valid
    private UserEditDataRequestDto user;

    @Size(max = 1000, message = "La biografía no puede exceder los 1000 caracteres")
    private String biography;
    @Min(value = 0, message = "Los años de experiencia no pueden ser negativos")
    @Max(value = 70, message = "Los años de experiencia no son válidos")
    private Integer yearsOfExperience;
    @Size(min = 10, max = 10, message = "El número de teléfono debe tener 10 caracteres")
    @Pattern(regexp = "\\d{10}", message = "El número de teléfono debe contener solo dígitos")
    private String phoneNumber;
    @Valid
    private SpecialityRequestDto speciality;

    public DoctorEditRequestDto() {
    }

    public DoctorEditRequestDto(UserEditDataRequestDto user, String biography, Integer yearsOfExperience, String phoneNumber, SpecialityRequestDto speciality) {
        this.user = user;
        this.biography = biography;
        this.yearsOfExperience = yearsOfExperience;
        this.phoneNumber = phoneNumber;
        this.speciality = speciality;
    }

    public UserEditDataRequestDto getUser() {
        return user;
    }

    public void setUser(UserEditDataRequestDto user) {
        this.user = user;
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
