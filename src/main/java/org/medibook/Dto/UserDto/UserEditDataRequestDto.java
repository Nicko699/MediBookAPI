package org.medibook.Dto.UserDto;

import jakarta.validation.constraints.NotBlank;

public class UserEditDataRequestDto {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;

    @NotBlank(message = "El apellido no puede estar vacío")
    private String  lastName;

    public UserEditDataRequestDto() {
    }

    public UserEditDataRequestDto(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
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
}
