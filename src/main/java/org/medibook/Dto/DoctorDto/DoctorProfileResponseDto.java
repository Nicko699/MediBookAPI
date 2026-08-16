package org.medibook.Dto.DoctorDto;

import org.medibook.Dto.SpecialityDto.SpecialityResponseDto;

public class DoctorProfileResponseDto {

    private Long id;
    private String biography;
    private Integer yearsOfExperience;
    private String phoneNumber;
    private SpecialityResponseDto speciality;

    public DoctorProfileResponseDto(Long id, String biography, Integer yearsOfExperience, String phoneNumber, SpecialityResponseDto speciality) {
        this.id = id;
        this.biography = biography;
        this.yearsOfExperience = yearsOfExperience;
        this.phoneNumber = phoneNumber;
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

    public SpecialityResponseDto getSpeciality() {
        return speciality;
    }

    public void setSpeciality(SpecialityResponseDto speciality) {
        this.speciality = speciality;
    }
}
