package org.medibook.Dto.UserDto;

import org.medibook.Dto.DoctorDto.DoctorProfileResponseDto;
import org.medibook.Dto.PatientDto.PatientProfileResponseDto;

public class UserProfileResponseDto {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private PatientProfileResponseDto patient;
    private DoctorProfileResponseDto doctor;

    public UserProfileResponseDto(Long id, String name, String lastName, String email, PatientProfileResponseDto patient, DoctorProfileResponseDto doctor) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.patient = patient;
        this.doctor = doctor;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PatientProfileResponseDto getPatient() {
        return patient;
    }

    public void setPatient(PatientProfileResponseDto patient) {
        this.patient = patient;
    }

    public DoctorProfileResponseDto getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorProfileResponseDto doctor) {
        this.doctor = doctor;
    }
}
