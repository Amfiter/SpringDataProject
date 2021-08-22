package com.syncretis.SpringDataProject.services;

import com.syncretis.SpringDataProject.converters.RoleConverter;
import com.syncretis.SpringDataProject.dto.RoleDTO;
import com.syncretis.SpringDataProject.entities.Role;
import com.syncretis.SpringDataProject.exceptions.RoleException;
import com.syncretis.SpringDataProject.repositories.RoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleConverter roleConverter;

    public RoleService(RoleRepository roleRepository, RoleConverter roleConverter) {
        this.roleRepository = roleRepository;
        this.roleConverter = roleConverter;
    }

    public List<RoleDTO> getRoles() {
        List<Role> listRole = roleRepository.findAll();
        List<RoleDTO> roleDTOS = roleConverter.entityToDto(listRole);
        return roleDTOS;
    }

    public RoleDTO getRoles(Long Id) {
        Role role = roleRepository.findById(Id).orElseThrow(() -> new RoleException(HttpStatus.NOT_FOUND));
        RoleDTO roleDTO = roleConverter.entityToDto(role);
        return roleDTO;
    }

    public Role addNewRoles(RoleDTO roleDTO) {
        Role role = roleConverter.dtoToEntity(roleDTO);
        return roleRepository.save(role);
    }

    public void deleteRole(Long id) {
        roleRepository.findById(id).orElseThrow(() -> new RoleException(HttpStatus.NOT_FOUND));
        roleRepository.deleteById(id);
    }

    public Role updateRole(RoleDTO newRole, Long id) {
        Role role;
        Role roleEntity = roleConverter.dtoToEntity(newRole);
        if (roleRepository.existsById(id)) {
            role = roleRepository.findById(id).orElse(null);
            role.setName(roleEntity.getName());
        } else {
            throw new RoleException(HttpStatus.NOT_FOUND);
        }
        return roleRepository.save(role);
    }
}
