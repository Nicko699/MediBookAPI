package org.medibook.Dto.DoctorDto;

import org.medibook.Dto.SpecialityDto.SpecialityResponseDto;
import org.medibook.Dto.UserDto.UserRegisterResponseDto;

public class DoctorRegisterResponseDto {

    private Long id;
    private String lastName;
    private String biography;
    private Integer yearsOfExperience;
    private String phoneNumber;
    private UserRegisterResponseDto userRegisterResponseDto;
    private SpecialityResponseDto speciality;

    public DoctorRegisterResponseDto() {
    }

    public DoctorRegisterResponseDto(Long id, String lastName, String biography, Integer yearsOfExperience, String phoneNumber, UserRegisterResponseDto userRegisterResponseDto, SpecialityResponseDto speciality) {
        this.id = id;
        this.lastName = lastName;
        this.biography = biography;
        this.yearsOfExperience = yearsOfExperience;
        this.phoneNumber = phoneNumber;
        this.userRegisterResponseDto = userRegisterResponseDto;
        this.speciality = speciality;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public UserRegisterResponseDto getUserRegisterResponseDto() {
        return userRegisterResponseDto;
    }

    public void setUserRegisterResponseDto(UserRegisterResponseDto userRegisterResponseDto) {
        this.userRegisterResponseDto = userRegisterResponseDto;
    }

    public SpecialityResponseDto getSpeciality() {
        return speciality;
    }

    public void setSpeciality(SpecialityResponseDto speciality) {
        this.speciality = speciality;
    }
}
