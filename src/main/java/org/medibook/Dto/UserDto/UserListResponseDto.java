package org.medibook.Dto.UserDto;

import org.medibook.Dto.RolDto.RolResponseDto;

import java.time.Instant;
import java.util.List;

public class UserListResponseDto {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private Boolean active;
    private Instant createdAt;
    private Instant updatedAt;

    private List<RolResponseDto>listRoles;

    public UserListResponseDto() {
    }

    public UserListResponseDto(Long id, String name, String lastName, String email, Boolean active, Instant createdAt, Instant updatedAt, List<RolResponseDto> listRoles) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<RolResponseDto> getListRoles() {
        return listRoles;
    }

    public void setListRoles(List<RolResponseDto> listRoles) {
        this.listRoles = listRoles;
    }
}
