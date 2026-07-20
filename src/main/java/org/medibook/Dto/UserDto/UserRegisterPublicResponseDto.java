package org.medibook.Dto.UserDto;

import org.medibook.Dto.PatientDto.PatientRegisterResponseDto;

public class UserRegisterPublicResponseDto {

    private Long id;
    private String name;
    private String email;
    private Boolean active;
    private PatientRegisterResponseDto patient;

    public UserRegisterPublicResponseDto() {
    }

    public UserRegisterPublicResponseDto(Long id, String name, String email, Boolean active, PatientRegisterResponseDto patient) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.active = active;
        this.patient = patient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public PatientRegisterResponseDto getPatient() {
        return patient;
    }

    public void setPatient(PatientRegisterResponseDto patient) {
        this.patient = patient;
    }
}
