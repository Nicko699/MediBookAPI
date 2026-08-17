package org.medibook.Dto.PatientDto;

import java.time.LocalDate;

public class PatientRegisterResponseDto {

    private Long id;
    private String gender;
    private String dni;
    private String phoneNumber;
    private LocalDate birthDate;
    private Boolean active;

    public PatientRegisterResponseDto() {
    }

    public PatientRegisterResponseDto(Long id, String gender, String dni, String phoneNumber, LocalDate birthDate, Boolean active) {
        this.id = id;
        this.gender = gender;
        this.dni = dni;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
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
}
