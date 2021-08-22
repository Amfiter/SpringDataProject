package com.syncretis.SpringDataProject.converters;

import com.syncretis.SpringDataProject.dto.UserDTO;
import com.syncretis.SpringDataProject.entities.Role;
import com.syncretis.SpringDataProject.entities.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class UserConverterTest {

    private final UserConverter userConverter = new UserConverter();

    @Test
    @DisplayName("shouldConvertEntityToDto")
    void entityToDto() {
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
        UserDTO actual = userConverter.entityToDto(user);

        //then
        assertThat(actual).isEqualTo(userDTO);
    }

    @Test
    @DisplayName("shouldConvertDtoToEntity")
    void dtoToEntity() {
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
        User actual = userConverter.dtoToEntity(userDTO);

        //then
        assertThat(actual).isEqualTo(user);
    }

    @Test
    @DisplayName("shouldConvertEntityListToDtoList")
    void entityListToDtoList() {
        //given
        Set<Role> roles = new HashSet<>();
        Role role = new Role(1L,"ADMIN");
        roles.add(role);

        List<UserDTO> expected = new ArrayList<>();
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

        expected.add(userDTO1);
        expected.add(userDTO2);

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
        List<UserDTO> actual = userConverter.entityToDto(userList);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("shouldConvertDtoListToEntityList")
    void dtoListToEntityList() {
        //given
        Set<Role> roles = new HashSet<>();
        Role role = new Role(1L,"ADMIN");
        roles.add(role);

        List<UserDTO> userListDTO = new ArrayList<>();
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

        userListDTO.add(userDTO1);
        userListDTO.add(userDTO2);

        List<User> expected = new ArrayList<>();
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

        //when
        List<User> actual = userConverter.dtoToEntity(userListDTO);

        //then
        expected.add(user1);
        expected.add(user2);

        assertThat(actual).isEqualTo(expected);
    }
}