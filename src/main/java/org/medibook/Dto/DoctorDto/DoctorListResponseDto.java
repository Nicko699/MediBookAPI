package org.medibook.Dto.DoctorDto;

import org.medibook.Dto.SpecialityDto.SpecialityResponseDto;
import org.medibook.Dto.UserDto.UserListDataResponseDto;



public class DoctorListResponseDto {

    private Long id;
    private String biography;
    private Integer yearsOfExperience;
    private String phoneNumber;
    private UserListDataResponseDto user;
    private SpecialityResponseDto speciality;

    public DoctorListResponseDto() {
    }

    public DoctorListResponseDto(Long id, String biography, Integer yearsOfExperience, String phoneNumber, UserListDataResponseDto user, SpecialityResponseDto speciality) {
        this.id = id;
        this.biography = biography;
        this.yearsOfExperience = yearsOfExperience;
        this.phoneNumber = phoneNumber;
        this.user = user;
        this.speciality = speciality;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public UserListDataResponseDto getUser() {
        return user;
    }

    public void setUser(UserListDataResponseDto user) {
        this.user = user;
    }

    public SpecialityResponseDto getSpeciality() {
        return speciality;
    }

    public void setSpeciality(SpecialityResponseDto speciality) {
        this.speciality = speciality;
    }
}
