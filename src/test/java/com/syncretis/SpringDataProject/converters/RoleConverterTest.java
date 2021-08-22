package com.syncretis.SpringDataProject.converters;

import com.syncretis.SpringDataProject.dto.RoleDTO;
import com.syncretis.SpringDataProject.entities.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RoleConverterTest {

    private final RoleConverter roleConverter = new RoleConverter();

    @Test
    @DisplayName("shouldConvertEntityToDto")
    void entityToDto() {
        //given
        Role role = new Role();
        role.setId(12L);
        role.setName("ADMIN");

        //when
        RoleDTO actual = roleConverter.entityToDto(role);

        //then
        RoleDTO expected = new RoleDTO();
        expected.setId(12L);
        expected.setName("ADMIN");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("shouldConvertDtoToEntity")
    void dtoToEntity() {
        //given
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(12L);
        roleDTO.setName("ADMIN");

        //when
        Role actual = roleConverter.dtoToEntity(roleDTO);

        //then
        Role expected = new Role();
        expected.setId(12L);
        expected.setName("ADMIN");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("shouldConvertEntityListToDtoList")
    void entityListToDtoList() {
        //given
        List<Role> roleList = new ArrayList<>();
        Role role1 = new Role();
        role1.setId(12L);
        role1.setName("ADMIN");

        Role role2 = new Role();
        role2.setId(10L);
        role2.setName("Dutch");

        roleList.add(role1);
        roleList.add(role2);

        //when
        List<RoleDTO> actual = roleConverter.entityToDto(roleList);

        //then
        List<RoleDTO> expected = new ArrayList<>();
        RoleDTO roleDTO1 = new RoleDTO();
        roleDTO1.setId(12L);
        roleDTO1.setName("ADMIN");

        RoleDTO roleDTO2 = new RoleDTO();
        roleDTO2.setId(10L);
        roleDTO2.setName("Dutch");

        expected.add(roleDTO1);
        expected.add(roleDTO2);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("shouldConvertDtoListToEntityList")
    void dtoListToEntityList() {
        //given
        List<RoleDTO> roleListDTO = new ArrayList<>();
        RoleDTO roleDTO1 = new RoleDTO();
        roleDTO1.setId(12L);
        roleDTO1.setName("ADMIN");

        RoleDTO roleDTO2 = new RoleDTO();
        roleDTO2.setId(10L);
        roleDTO2.setName("Dutch");

        roleListDTO.add(roleDTO1);
        roleListDTO.add(roleDTO2);

        //when
        List<Role> actual = roleConverter.dtoToEntity(roleListDTO);

        //then
        List<Role> expected = new ArrayList<>();
        Role role1 = new Role();
        role1.setId(12L);
        role1.setName("ADMIN");

        Role role2 = new Role();
        role2.setId(10L);
        role2.setName("Dutch");

        expected.add(role1);
        expected.add(role2);

        assertThat(actual).isEqualTo(expected);
    }
}