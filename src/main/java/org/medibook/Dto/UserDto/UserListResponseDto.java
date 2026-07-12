package org.medibook.Dto.UserDto;

import org.medibook.Dto.RolDto.RolResponseDto;

import java.util.List;

public class UserListResponseDto {
    private Long id;
    private String name;
    private String email;
    private Boolean active;

    private List<RolResponseDto>listRoles;

    public UserListResponseDto() {
    }

    public UserListResponseDto(Long id, String name, String email, Boolean active, List<RolResponseDto> listRoles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.active = active;
        this.listRoles = listRoles;
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

    public List<RolResponseDto> getListRoles() {
        return listRoles;
    }

    public void setListRoles(List<RolResponseDto> listRoles) {
        this.listRoles = listRoles;
    }
}
