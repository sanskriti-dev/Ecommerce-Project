package com.tothenew.ecommerceapp.security;

import org.springframework.security.core.GrantedAuthority;

public class GrantAuthorityImpl implements GrantedAuthority {

    String authority;

    public GrantAuthorityImpl(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    @Override
    public String toString() {
        return "GrantAuthorityImpl{" +
                "authority='" + authority + '\'' +
                '}';
    }
}