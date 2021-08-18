package com.syncretis.SpringDataProject.controllers;

import com.syncretis.SpringDataProject.dto.UserDTO;
import com.syncretis.SpringDataProject.entities.User;
import com.syncretis.SpringDataProject.services.UserService;
import com.syncretis.SpringDataProject.util.Marker;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping(path = "api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(path = "{id}")
    public UserDTO getRoles(@PathVariable("id") Long id) {
        return userService.getUsers(id);
    }

    @Validated({Marker.OnCreate.class})
    @PostMapping
    public void createNewRole(@Valid @RequestBody UserDTO userDTO) {
        userService.addNewUsers(userDTO);
    }

    @DeleteMapping(path = "{id}")
    public void deleteRole(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

    @Validated({Marker.OnUpdate.class})
    @PutMapping(path = "{id}")
    public User updateRole(@Valid @RequestBody UserDTO userDTO, @PathVariable("id") Long id) {
        return userService.updateUser(userDTO, id);
    }
}