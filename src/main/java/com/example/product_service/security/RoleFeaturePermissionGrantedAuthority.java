package com.example.digid.digid.productservice.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.Map;

public class RoleFeaturePermissionGrantedAuthority implements GrantedAuthority {

    private final String role;
    private final Map<String, Map<String, Boolean>> featuresPermissions;

    public RoleFeaturePermissionGrantedAuthority(String role, Map<String, Map<String, Boolean>> featuresPermissions) {
        this.role = role;
        this.featuresPermissions = featuresPermissions;
    }

    @Override
    public String getAuthority() {
        return role;
    }

    public Map<String, Map<String, Boolean>> getFeaturesPermissions() {
        return featuresPermissions;
    }

    // You can add methods to check specific permissions
    public boolean hasPermission(String feature, String permission) {
        if (featuresPermissions.containsKey(feature)) {
            return featuresPermissions.get(feature).getOrDefault(permission, false);
        }
        return false;
    }

    @Override
    public String toString() {
        return "RoleFeaturePermissionGrantedAuthority{" +
                "role='" + role + '\'' +
                ", featuresPermissions=" + featuresPermissions +
                '}';
    }
}
