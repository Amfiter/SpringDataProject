package com.syncretis.SpringDataProject.converters;

import com.syncretis.SpringDataProject.dto.RoleDTO;
import com.syncretis.SpringDataProject.entities.Role;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleConverter {

    public RoleDTO entityToDto(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        return roleDTO;
    }

    public List<RoleDTO> entityToDto(List<Role> role) {
        return role.stream().map(roleEntity -> entityToDto(roleEntity)).collect(Collectors.toList());
    }

    public Role dtoToEntity(RoleDTO roleDTO) {
        Role role = new Role();
        role.setId(roleDTO.getId());
        role.setName(roleDTO.getName());
        return role;
    }

    public List<Role> dtoToEntity(List<RoleDTO> roleDTO) {
        return roleDTO.stream().map(DTO -> dtoToEntity(DTO)).collect(Collectors.toList());
    }
}
