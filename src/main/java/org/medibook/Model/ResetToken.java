package org.medibook.Model;

import jakarta.persistence.*;

import java.time.Instant;
@Entity
public class ResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String  resetTokenId;
    private String resetTokenEncript;
    private Boolean active;
    private Instant createdAt;
    private Instant expiredAt;

   @OneToOne(mappedBy = "resetToken",cascade = CascadeType.ALL)
    private User user;

    public ResetToken() {
    }

    public ResetToken(Long id, String resetTokenId, String resetTokenEncript, Boolean active, Instant createdAt, Instant expiredAt, User user) {
        this.id = id;
        this.resetTokenId = resetTokenId;
        this.resetTokenEncript = resetTokenEncript;
        this.active = active;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResetTokenId() {
        return resetTokenId;
    }

    public void setResetTokenId(String resetTokenId) {
        this.resetTokenId = resetTokenId;
    }

    public String getResetTokenEncript() {
        return resetTokenEncript;
    }

    public void setResetTokenEncript(String resetTokenEncript) {
        this.resetTokenEncript = resetTokenEncript;
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

    public Instant getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(Instant expiredAt) {
        this.expiredAt = expiredAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
