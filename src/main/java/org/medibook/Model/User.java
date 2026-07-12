package org.medibook.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
@Entity
public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private Boolean active;
    private Boolean softDelete;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @JoinTable(name = "User_Rol", joinColumns = @JoinColumn(name = "User_id"),
            inverseJoinColumns = @JoinColumn(name = "Rol_id"))
    @ManyToMany
    private List<Rol>listRoles;

   @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<RefreshToken>refreshTokenList;

   @OneToOne(mappedBy = "user")
   private ResetToken resetToken;

   @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
   private Patient patient;

    public User() {
    }

    public User(Long id, String name, String email, String password, Boolean active, Boolean softDelete, LocalDateTime createdAt, LocalDateTime updatedAt, List<Rol> listRoles, List<RefreshToken> refreshTokenList, ResetToken resetToken) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.active = active;
        this.softDelete = softDelete;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.listRoles = listRoles;
        this.refreshTokenList = refreshTokenList;
        this.resetToken = resetToken;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getSoftDelete() {
        return softDelete;
    }

    public void setSoftDelete(Boolean softDelete) {
        this.softDelete = softDelete;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Rol> getListRoles() {
        return listRoles;
    }

    public void setListRoles(List<Rol> listRoles) {
        this.listRoles = listRoles;
    }

    public List<RefreshToken> getRefreshTokenList() {
        return refreshTokenList;
    }

    public void setRefreshTokenList(List<RefreshToken> refreshTokenList) {
        this.refreshTokenList = refreshTokenList;
    }

    public ResetToken getResetToken() {
        return resetToken;
    }

    public void setResetToken(ResetToken resetToken) {
        this.resetToken = resetToken;
    }
}
