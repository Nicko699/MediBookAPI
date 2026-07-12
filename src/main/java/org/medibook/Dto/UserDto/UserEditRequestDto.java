package org.medibook.Dto.UserDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class UserEditRequestDto {
    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;
    @NotNull(message = "El estado no puede ser nulo")
    private Boolean active;
    @NotNull(message = "La lista de roles no puede ser nula")
    @Size(min = 1, message = "Debe seleccionar al menos un rol")
    private List<Long> rolId;

    public UserEditRequestDto() {
    }

    public UserEditRequestDto(String name, Boolean active, List<Long> rolId) {
        this.name = name;
        this.active = active;
        this.rolId = rolId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Long> getRolId() {
        return rolId;
    }

    public void setRolId(List<Long> rolId) {
        this.rolId = rolId;
    }
}
