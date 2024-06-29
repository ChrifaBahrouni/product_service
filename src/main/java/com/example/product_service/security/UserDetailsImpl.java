package com.example.digid.digid.productservice.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class UserDetailsImpl implements UserDetails {

    private String username;
    private Map<String, Map<String, Map<String, Boolean>>> rolesFeaturesPermissions;
    private Collection<GrantedAuthority> authorities = new ArrayList<>();

    public UserDetailsImpl(String username, Map<String, Map<String, Map<String, Boolean>>> rolesFeaturesPermissions) {
        this.username = username;
        this.rolesFeaturesPermissions = rolesFeaturesPermissions;
        this.authorities = convertToAuthorities(rolesFeaturesPermissions);
    }

    public Map<String, Map<String, Map<String, Boolean>>> getRolesFeaturesPermissions() {
        return rolesFeaturesPermissions;
    }

    private Set<GrantedAuthority> convertToAuthorities(Map<String, Map<String, Map<String, Boolean>>> rolesFeaturesPermissions) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Map.Entry<String, Map<String, Map<String, Boolean>>> roleEntry : rolesFeaturesPermissions.entrySet()) {
            String role = roleEntry.getKey();
            Map<String, Map<String, Boolean>> featuresPermissions = roleEntry.getValue();
            authorities.add(new RoleFeaturePermissionGrantedAuthority(role, featuresPermissions));
        }
        return authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
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
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
