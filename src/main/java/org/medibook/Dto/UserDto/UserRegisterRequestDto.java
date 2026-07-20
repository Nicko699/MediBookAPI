package org.medibook.Dto.UserDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.medibook.Dto.DoctorDto.DoctorRegisterRequestDto;

import java.util.List;

public class UserRegisterRequestDto {
    @NotBlank(message = "Debe ingresar un nombre")
    private String name;
    @Email(message = "El correo electrónico debe estar en un formato válido")
    @NotBlank(message = "Debe ingresar un correo electrónico")
    private String email;
    @Size(min = 8, max = 20, message = "La contraseña debe tener entre 8 a 20 caracteres")
    @NotBlank(message = "Debe ingresar una contraseña")
    private String password;
    @Size(min = 1, message = "debe ingresar al menos 1 rol")
    private List<Long> roleIds;
    private DoctorRegisterRequestDto doctor;

    public UserRegisterRequestDto() {
    }

    public UserRegisterRequestDto(String name, String email, String password, List<Long> roleIds, DoctorRegisterRequestDto doctor) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roleIds = roleIds;
        this.doctor = doctor;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }

    public DoctorRegisterRequestDto getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorRegisterRequestDto doctor) {
        this.doctor = doctor;
    }
}
