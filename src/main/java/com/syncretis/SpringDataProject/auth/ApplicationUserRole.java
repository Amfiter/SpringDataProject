package com.syncretis.SpringDataProject.auth;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.syncretis.SpringDataProject.auth.ApplicationUserPermission.*;


public enum ApplicationUserRole {
    USER(List.of(USER_READ)),
    ADMIN(List.of(ADMIN_READ,ADMIN_WRITE));

    private final List<ApplicationUserPermission> permissions;

    ApplicationUserRole(List<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public List<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public List<SimpleGrantedAuthority> getGrantedAuthorities(){
        List<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
