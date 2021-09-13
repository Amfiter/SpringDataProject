package com.syncretis.SpringDataProject.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.syncretis.SpringDataProject.entities.Role;
import org.springframework.security.core.GrantedAuthority;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

public class UserDTO {

    @Null(message = "should be null")
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
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

    @Override
    public String toString() {
        return new StringJoiner(", ", UserDTO.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("username='" + username + "'")
                .add("password='" + password + "'")
                .add("city='" + city + "'")
                .add("roles=" + roles)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id) && Objects.equals(username, userDTO.username) && Objects.equals(password, userDTO.password) && Objects.equals(city, userDTO.city) && Objects.equals(roles, userDTO.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, city, roles);
    }
}