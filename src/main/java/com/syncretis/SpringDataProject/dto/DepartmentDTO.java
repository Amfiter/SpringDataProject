package com.syncretis.SpringDataProject.dto;

import com.syncretis.SpringDataProject.util.Marker;

import javax.validation.constraints.*;
import java.util.Objects;

public class DepartmentDTO {

    @Null(groups = Marker.OnCreate.class, message = "should be null")
    @NotNull(groups = Marker.OnUpdate.class, message = "should be not null")
    private Long id;

    @NotBlank(message = "should be not blank")
    @Pattern(regexp = "[A-Za-z ]*", message = "should only contain letters")
    private String name;

    @Future(message = "should not be in the past")
    public DepartmentDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DepartmentDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentDTO that = (DepartmentDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
