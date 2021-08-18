package com.syncretis.SpringDataProject.controllers;

import com.syncretis.SpringDataProject.dto.RoleDTO;
import com.syncretis.SpringDataProject.entities.Role;
import com.syncretis.SpringDataProject.services.RoleService;
import com.syncretis.SpringDataProject.util.Marker;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping(path = "api/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public List<RoleDTO> getRoles() {
        return roleService.getRoles();
    }

    @GetMapping(path = "{id}")
    public RoleDTO getRoles(@PathVariable("id") Long id) {
        return roleService.getRoles(id);
    }

    @Validated({Marker.OnCreate.class})
    @PostMapping
    public void createNewRole(@Valid @RequestBody RoleDTO roleDTO) {
        roleService.addNewRoles(roleDTO);
    }

    @DeleteMapping(path = "{id}")
    public void deleteRole(@PathVariable("id") Long id) {
        roleService.deleteRole(id);
    }

    @Validated({Marker.OnUpdate.class})
    @PutMapping(path = "{id}")
    public Role updateRole(@Valid @RequestBody RoleDTO roleDTO, @PathVariable("id") Long id) {
        return roleService.updateRole(roleDTO, id);
    }
}