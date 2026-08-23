package org.medibook.Dto.UserDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.medibook.Dto.DoctorDto.DoctorProfileEditRequestDto;
import org.medibook.Dto.PatientDto.PatientProfileEditRequestDto;

public class UserProfileEditRequestDto {

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String name;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres")
    private String lastName;

    @Size(min = 8, message = "La contraseña actual debe tener al menos 8 caracteres")
    private String oldPassword;

    @Size(min = 8, message = "La nueva contraseña debe tener al menos 8 caracteres")
    private String newPassword;

    @Valid
    private PatientProfileEditRequestDto patientProfileEditRequestDto;

    @Valid
    private DoctorProfileEditRequestDto doctorProfileEditRequestDto;

    public UserProfileEditRequestDto() {
    }

    public UserProfileEditRequestDto(String name, String lastName, String oldPassword, String newPassword,
                                     PatientProfileEditRequestDto patientProfileEditRequestDto,
                                     DoctorProfileEditRequestDto doctorProfileEditRequestDto) {
        this.name = name;
        this.lastName = lastName;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.patientProfileEditRequestDto = patientProfileEditRequestDto;
        this.doctorProfileEditRequestDto = doctorProfileEditRequestDto;
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

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public PatientProfileEditRequestDto getPatientProfileEditRequestDto() {
        return patientProfileEditRequestDto;
    }

    public void setPatientProfileEditRequestDto(PatientProfileEditRequestDto patientProfileEditRequestDto) {
        this.patientProfileEditRequestDto = patientProfileEditRequestDto;
    }

    public DoctorProfileEditRequestDto getDoctorProfileEditRequestDto() {
        return doctorProfileEditRequestDto;
    }

    public void setDoctorProfileEditRequestDto(DoctorProfileEditRequestDto doctorProfileEditRequestDto) {
        this.doctorProfileEditRequestDto = doctorProfileEditRequestDto;
    }
}