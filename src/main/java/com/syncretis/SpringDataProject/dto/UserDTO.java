package com.syncretis.SpringDataProject.dto;

import com.syncretis.SpringDataProject.entities.Role;
import com.syncretis.SpringDataProject.util.Marker;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.Set;

public class UserDTO implements UserDetails {

    @Null(groups = Marker.OnCreate.class, message = "should be null")
    @NotNull(groups = Marker.OnUpdate.class, message = "should be not null")
    private Long id;

    @NotBlank(message = "should be not blank")
    @Pattern(regexp = "[A-Za-z ]*", message = "should only contain letters")
    private String username;

    @NotBlank(message = "should be not blank")
    private String password;

    @NotBlank(message = "should be not blank")
    @Pattern(regexp = "[A-Za-z ]*", message = "should only contain letters")
    private String city;

    @Valid
    private Set<Role> roles;

    public UserDTO() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}