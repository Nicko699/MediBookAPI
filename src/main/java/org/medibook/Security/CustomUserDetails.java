package org.medibook.Security;

import jakarta.annotation.Nonnull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private final String email;
    private final String userName;
    private final String password;
    private final boolean isEnabled;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(String email, String userName, String password, boolean isEnabled, Collection<? extends GrantedAuthority> authorities) {
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.isEnabled = isEnabled;
        this.authorities = authorities;
    }

    public String getName() {
        return userName;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public @Nonnull Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}

