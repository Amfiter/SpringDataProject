package com.syncretis.SpringDataProject.services;

import com.syncretis.SpringDataProject.converters.UserConverter;
import com.syncretis.SpringDataProject.dto.UserDTO;
import com.syncretis.SpringDataProject.entities.User;
import com.syncretis.SpringDataProject.exceptions.UserException;
import com.syncretis.SpringDataProject.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userDetailService")
public class UserService implements UserDetailsService {
    
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    
    public UserService(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    public List<UserDTO> getUsers() {
        List<User> listUser = userRepository.findAll();
        List<UserDTO> userDTOS = userConverter.entityToDto(listUser);
        return userDTOS;
    }

    public UserDTO getUsers(Long Id) {
        User user = userRepository.findById(Id).orElseThrow(() -> new UserException(HttpStatus.NOT_FOUND));
        UserDTO userDTO = userConverter.entityToDto(user);
        return userDTO;
    }

    public User addNewUsers(UserDTO userDTO) {
        User user = userConverter.dtoToEntity(userDTO);
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.findById(id).orElseThrow(() -> new UserException(HttpStatus.NOT_FOUND));
        userRepository.deleteById(id);
    }

    public User updateUser(UserDTO newUser, Long id) {
        User user;
        User userEntity = userConverter.dtoToEntity(newUser);
        if (userRepository.existsById(id)) {
            user = userRepository.findById(id).orElse(null);
            user.setUsername(userEntity.getUsername());
            user.setPassword(userEntity.getPassword());
            user.setCity(userEntity.getCity());
            user.setRoles(userEntity.getRoles());
        } else {
            throw new UserException(HttpStatus.NOT_FOUND);
        }
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User does not exist"));
        return user;
    }
}
