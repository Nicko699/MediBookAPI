package org.medibook.Dto.PatientDto;

import org.medibook.Dto.UserDto.UserListDataResponseDto;

import java.time.LocalDate;

public class PatientListResponseDto {

    private Long id;
    private String dni;
    private String gender;
    private String phoneNumber;
    private LocalDate birthDate;
    private UserListDataResponseDto user;

    public PatientListResponseDto() {
    }

    public PatientListResponseDto(Long id, String dni, String gender, String phoneNumber, LocalDate birthDate, UserListDataResponseDto user) {
        this.id = id;
        this.dni = dni;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public UserListDataResponseDto getUser() {
        return user;
    }

    public void setUser(UserListDataResponseDto user) {
        this.user = user;
    }
}
