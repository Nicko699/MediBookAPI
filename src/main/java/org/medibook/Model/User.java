package org.medibook.Model;

import jakarta.persistence.*;

import java.time.Instant;
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
    private Instant createdAt;
    private Instant updatedAt;

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

   @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
   private Doctor doctor;

    public User() {
    }

    public User(Long id, String name, String email, String password, Boolean active, Boolean softDelete, Instant createdAt, Instant updatedAt, List<Rol> listRoles, List<RefreshToken> refreshTokenList, ResetToken resetToken, Patient patient, Doctor doctor) {
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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
