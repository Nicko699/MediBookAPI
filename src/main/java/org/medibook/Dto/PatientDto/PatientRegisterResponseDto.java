package org.medibook.Dto.PatientDto;

import org.medibook.Dto.UserDto.UserRegisterResponseDto;

import java.time.LocalDate;

public class PatientRegisterResponseDto {

    private Long id;
    private String lastName;
    private Long dni;
    private String phoneNumber;
    private LocalDate birthDate;
    private Boolean active;
    private UserRegisterResponseDto userRegister;

    public PatientRegisterResponseDto() {
    }

    public PatientRegisterResponseDto(String lastName, LocalDate birthDate, Boolean active, Long id, Long dni, String phoneNumber, UserRegisterResponseDto userRegister) {
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.active = active;
        this.id = id;
        this.dni = dni;
        this.phoneNumber = phoneNumber;
        this.userRegister = userRegister;
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

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public UserRegisterResponseDto getUserRegister() {
        return userRegister;
    }

    public void setUserRegister(UserRegisterResponseDto userRegister) {
        this.userRegister = userRegister;
    }
}
