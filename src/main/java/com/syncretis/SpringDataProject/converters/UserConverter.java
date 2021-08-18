package com.syncretis.SpringDataProject.converters;

import com.syncretis.SpringDataProject.dto.UserDTO;
import com.syncretis.SpringDataProject.entities.User;
import com.syncretis.SpringDataProject.services.RoleService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter {

    private final RoleService userConverter;

    private final RoleConverter roleConverter;

    public UserConverter(RoleService userConverter, RoleConverter roleConverter) {
        this.userConverter = userConverter;
        this.roleConverter = roleConverter;
    }


    public UserDTO entityToDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setCity(user.getCity());
        userDTO.setRoles(user.getRoles());
        return userDTO;
    }

    public List<UserDTO> entityToDto(List<User> user) {
        return user.stream().map(personEntity -> entityToDto(personEntity)).collect(Collectors.toList());
    }

    public User dtoToEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setCity(userDTO.getCity());
        user.setRoles(userDTO.getRoles());
        return user;
    }

    public List<User> dtoToEntity(List<UserDTO> userDTO) {
        return userDTO.stream().map(DTO -> dtoToEntity(DTO)).collect(Collectors.toList());
    }
}
