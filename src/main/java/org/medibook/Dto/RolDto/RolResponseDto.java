package org.medibook.Dto.RolDto;

public class RolResponseDto {
    private Long id;
    private String name;

    public RolResponseDto() {
    }

    public RolResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
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
}
