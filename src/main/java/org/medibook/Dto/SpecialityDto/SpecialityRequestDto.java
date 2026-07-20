package org.medibook.Dto.SpecialityDto;

import jakarta.validation.constraints.NotBlank;

public class SpecialityRequestDto {
    @NotBlank(message = "El nombre de la especialidad es obligatorio")
    private String name;
    @NotBlank(message = "La descripción de la especialidad es obligatoria")
    private String description;

    public SpecialityRequestDto() {
    }

    public SpecialityRequestDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
