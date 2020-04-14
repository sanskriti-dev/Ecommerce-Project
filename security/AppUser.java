package com.tothenew.ecommerceapp.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AppUser implements UserDetails {

    private String username;
    private String password;
    List<GrantAuthorityImpl> grantAuthorities;
    private boolean isEnabled;
    private boolean isAccountNonLocked;
    private boolean isPasswordExpired;

    public AppUser(String username, String password, List<GrantAuthorityImpl> grantAuthorities,boolean isEnabled,boolean isAccountNonLocked,boolean isPasswordExpired) {
        this.username = username;
        this.password = password;
        this.grantAuthorities = grantAuthorities;
        this.isEnabled = isEnabled;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isPasswordExpired = isPasswordExpired;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isPasswordExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", grantAuthorities=" + grantAuthorities +
                ", isEnabled=" + isEnabled +
                ", isAccountNonLocked=" + isAccountNonLocked +
                ", isPasswordExpired=" + isPasswordExpired +
                '}';
    }
}