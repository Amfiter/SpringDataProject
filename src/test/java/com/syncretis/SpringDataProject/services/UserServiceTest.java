package com.syncretis.SpringDataProject.services;

import com.syncretis.SpringDataProject.converters.UserConverter;
import com.syncretis.SpringDataProject.dto.UserDTO;
import com.syncretis.SpringDataProject.entities.Role;
import com.syncretis.SpringDataProject.entities.User;
import com.syncretis.SpringDataProject.exceptions.UserException;
import com.syncretis.SpringDataProject.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith({MockitoExtension.class})
class UserServiceTest {

    @Mock
    UserRepository userRepository;
    @Mock
    UserConverter userConverter;

    @InjectMocks
    UserService userService;

    @Test
    @DisplayName("shouldReturnAllUsers")
    void getUsers() {
        //given
        Set<Role> roles = new HashSet<>();
        Role role = new Role(1L,"ADMIN");
        roles.add(role);

        List<UserDTO> userDTOList = new ArrayList<>();
        UserDTO userDTO1 = new UserDTO();
        userDTO1.setId(12L);
        userDTO1.setUsername("Vladimir");
        userDTO1.setPassword("123");
        userDTO1.setCity("Bishkek");
        userDTO1.setRoles(roles);

        UserDTO userDTO2 = new UserDTO();
        userDTO2.setId(11L);
        userDTO2.setUsername("Nikita");
        userDTO2.setPassword("123");
        userDTO2.setCity("Tomsk");
        userDTO2.setRoles(roles);

        userDTOList.add(userDTO1);
        userDTOList.add(userDTO2);

        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setId(12L);
        user1.setUsername("Vladimir");
        user1.setPassword("123");
        user1.setCity("Bishkek");
        user1.setRoles(roles);

        User user2 = new User();
        user2.setId(11L);
        user2.setUsername("Nikita");
        user2.setPassword("123");
        user2.setCity("Tomsk");
        user2.setRoles(roles);

        userList.add(user1);
        userList.add(user2);

        //when
        Mockito.when(userRepository.findAll()).thenReturn(userList);
        Mockito.when(userConverter.entityToDto(userList)).thenReturn(userDTOList);
        List<UserDTO> actualUsersDto = userService.getUsers();

        //then
        Mockito.verify(userRepository).findAll();
        Mockito.verify(userConverter).entityToDto(userList);
        assertThat(actualUsersDto).isEqualTo(userDTOList);
    }

    @Test
    @DisplayName("shouldReturnUserById")
    void getUserById() {
        //given
        Set<Role> roles = new HashSet<>();
        Role role = new Role(1L,"ADMIN");
        roles.add(role);

        User user = new User();
        user.setId(12L);
        user.setUsername("Vladimir");
        user.setPassword("123");
        user.setCity("Bishkek");
        user.setRoles(roles);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(12L);
        userDTO.setUsername("Vladimir");
        userDTO.setPassword("123");
        userDTO.setCity("Bishkek");
        userDTO.setRoles(roles);

        //when
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Mockito.when(userConverter.entityToDto(user)).thenReturn(userDTO);
        UserDTO actualUserDto = userService.getUsers(1L);

        //then
        Mockito.verify(userRepository).findById(1L);
        Mockito.verify(userConverter).entityToDto(user);
        assertThat(actualUserDto).isEqualTo(userDTO);
    }

    @Test
    @DisplayName("shouldThrowNotFoundErrorFindById")
    void getUserError() {
        //when
        Mockito.when(userRepository.findById(1L)).thenThrow(new UserException(HttpStatus.NOT_FOUND));

        //then
        assertThatThrownBy(() -> userService.getUsers(1L))
                .isInstanceOf(UserException.class);
        Mockito.verify(userRepository).findById(1L);
    }

    @Test
    @DisplayName("shouldThrowNotFoundErrorDeleteById")
    void deleteUserError() {
        //when
        Mockito.when(userRepository.findById(1L)).thenThrow(new UserException(HttpStatus.NOT_FOUND));

        //then
        assertThatThrownBy(() -> userService.deleteUser(1L))
                .isInstanceOf(UserException.class);
        Mockito.verify(userRepository).findById(1L);
    }

    @Test
    @DisplayName("shouldSaveUser")
    void addNewUser() {
        //given
        Set<Role> roles = new HashSet<>();
        Role role = new Role(1L,"ADMIN");
        roles.add(role);

        User user = new User();
        user.setId(12L);
        user.setUsername("Vladimir");
        user.setPassword("123");
        user.setCity("Bishkek");
        user.setRoles(roles);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(12L);
        userDTO.setUsername("Vladimir");
        userDTO.setPassword("123");
        userDTO.setCity("Bishkek");
        userDTO.setRoles(roles);

        //when
        Mockito.when(userConverter.dtoToEntity(userDTO)).thenReturn(user);
        Mockito.when(userRepository.save(user)).thenReturn(user);

        User actualUser = userService.addNewUsers(userDTO);

        //then
        Mockito.verify(userConverter).dtoToEntity(userDTO);
        Mockito.verify(userRepository).save(user);
        assertThat(actualUser).isEqualTo(user);
    }

    @Test
    @DisplayName("ShouldDeleteUserById")
    void deleteUser() {
        //given
        Set<Role> roles = new HashSet<>();
        Role role = new Role(1L,"ADMIN");
        roles.add(role);

        User user = new User();
        user.setId(12L);
        user.setUsername("Vladimir");
        user.setPassword("123");
        user.setCity("Bishkek");
        user.setRoles(roles);

        //when
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        userService.deleteUser(1L);

        //then
        Mockito.verify(userRepository).findById(1L);
        Mockito.verify(userRepository).deleteById(1L);
    }

    @Test
    @DisplayName("shouldReturnUpdateUserIfIsExist")
    void updateUser1() {
        //given
        Set<Role> roles = new HashSet<>();
        Role role = new Role(1L,"ADMIN");
        roles.add(role);

        User user = new User();
        user.setId(12L);
        user.setUsername("Vladimir");
        user.setPassword("123");
        user.setCity("Bishkek");
        user.setRoles(roles);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(12L);
        userDTO.setUsername("Vladimir");
        userDTO.setPassword("123");
        userDTO.setCity("Bishkek");
        userDTO.setRoles(roles);

        //when
        Mockito.when(userConverter.dtoToEntity(userDTO)).thenReturn(user);
        Mockito.when(userRepository.existsById(1L)).thenReturn(true);
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(user)).thenReturn(user);

        User actualUser = userService.updateUser(userDTO, 1L);

        //then
        Mockito.verify(userRepository).save(user);
        Mockito.verify(userRepository).findById(1L);
        Mockito.verify(userConverter).dtoToEntity(userDTO);
        assertThat(actualUser).isEqualTo(user);
    }

    @Test
    @DisplayName("shouldReturnUpdateUserIfIsNotExist")
    void updateUser2() {
        //given
        Set<Role> roles = new HashSet<>();
        Role role = new Role(1L,"ADMIN");
        roles.add(role);

        User user = new User();
        user.setId(12L);
        user.setUsername("Vladimir");
        user.setPassword("123");
        user.setCity("Bishkek");
        user.setRoles(roles);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(12L);
        userDTO.setUsername("Vladimir");
        userDTO.setPassword("123");
        userDTO.setCity("Bishkek");
        userDTO.setRoles(roles);

        //when
        Mockito.when(userConverter.dtoToEntity(userDTO)).thenReturn(user);
        Mockito.when(userRepository.existsById(1L)).thenReturn(false);

        //then
        assertThatThrownBy(() -> userService.updateUser(userDTO, 1L)).isExactlyInstanceOf(UserException.class);

    }
}