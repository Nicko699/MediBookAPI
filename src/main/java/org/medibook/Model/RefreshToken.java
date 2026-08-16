package org.medibook.Model;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
public class RefreshToken {
    @Id
    @GeneratedValue( strategy=GenerationType.IDENTITY)
    private Long id;
    private String  refreshTokenId;
    private String refreshTokenEncript;
    private Boolean active;
    private Instant createdAt;
    private Instant expiredAt;

    @ManyToOne()
    private User user;

    public RefreshToken() {
    }

    public RefreshToken(Long id, String refreshTokenId, String refreshTokenEncript, Boolean active, Instant createdAt, Instant expiredAt, User user) {
        this.id = id;
        this.refreshTokenId = refreshTokenId;
        this.refreshTokenEncript = refreshTokenEncript;
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

    public String getRefreshTokenId() {
        return refreshTokenId;
    }

    public void setRefreshTokenId(String refreshTokenId) {
        this.refreshTokenId = refreshTokenId;
    }

    public String getRefreshTokenEncript() {
        return refreshTokenEncript;
    }

    public void setRefreshTokenEncript(String refreshTokenEncript) {
        this.refreshTokenEncript = refreshTokenEncript;
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
