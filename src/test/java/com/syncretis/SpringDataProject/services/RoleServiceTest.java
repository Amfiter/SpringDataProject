package com.syncretis.SpringDataProject.services;

import com.syncretis.SpringDataProject.converters.RoleConverter;
import com.syncretis.SpringDataProject.dto.RoleDTO;
import com.syncretis.SpringDataProject.entities.Role;
import com.syncretis.SpringDataProject.exceptions.RoleException;
import com.syncretis.SpringDataProject.repositories.RoleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith({MockitoExtension.class})
class RoleServiceTest {
    @Mock
    RoleRepository roleRepository;
    @Mock
    RoleConverter roleConverter;

    @InjectMocks
    RoleService roleService;

    @Test
    @DisplayName("shouldReturnAllRoles")
    void getRoles() {
        //given
        List<Role> roleList = new ArrayList<>();
        Role role = new Role();
        role.setName("ADMIN");
        roleList.add(role);

        List<RoleDTO> roleDTOList = new ArrayList<>();
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setName("ADMIN");
        roleDTOList.add(roleDTO);

        //when
        Mockito.when(roleRepository.findAll()).thenReturn(roleList);
        Mockito.when(roleConverter.entityToDto(roleList)).thenReturn(roleDTOList);
        List<RoleDTO> actualRolesDto = roleService.getRoles();

        //then
        Mockito.verify(roleRepository).findAll();
        Mockito.verify(roleConverter).entityToDto(roleList);
        assertThat(actualRolesDto).isEqualTo(roleDTOList);
    }

    @Test
    @DisplayName("shouldReturnRoleById")
    void getRoleById() {
        //given
        Role role = new Role();
        role.setName("ADMIN");

        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setName("ADMIN");

        //when
        Mockito.when(roleRepository.findById(1L)).thenReturn(Optional.of(role));
        Mockito.when(roleConverter.entityToDto(role)).thenReturn(roleDTO);
        RoleDTO actualRoleDto = roleService.getRoles(1L);

        //then
        Mockito.verify(roleRepository).findById(1L);
        Mockito.verify(roleConverter).entityToDto(role);
        assertThat(actualRoleDto).isEqualTo(roleDTO);
    }

    @Test
    @DisplayName("shouldThrowNotFoundErrorFindById")
    void getRoleError() {
        //when
        Mockito.when(roleRepository.findById(1L)).thenThrow(new RoleException(HttpStatus.NOT_FOUND));

        //then
        assertThatThrownBy(() -> roleService.getRoles(1L))
                .isInstanceOf(RoleException.class);
        Mockito.verify(roleRepository).findById(1L);
    }

    @Test
    @DisplayName("shouldThrowNotFoundErrorDeleteById")
    void deleteRoleError() {
        //when
        Mockito.when(roleRepository.findById(1L)).thenThrow(new RoleException(HttpStatus.NOT_FOUND));

        //then
        assertThatThrownBy(() -> roleService.deleteRole(1L))
                .isInstanceOf(RoleException.class);
        Mockito.verify(roleRepository).findById(1L);
    }

    @Test
    @DisplayName("shouldSaveRole")
    void addNewRole() {
        //given
        Role role = new Role();
        role.setName("ADMIN");

        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setName("ADMIN");

        //when
        Mockito.when(roleConverter.dtoToEntity(roleDTO)).thenReturn(role);
        Mockito.when(roleRepository.save(role)).thenReturn(role);

        Role actualRole = roleService.addNewRoles(roleDTO);

        //then
        Mockito.verify(roleConverter).dtoToEntity(roleDTO);
        Mockito.verify(roleRepository).save(role);
        assertThat(actualRole).isEqualTo(role);
    }

    @Test
    @DisplayName("ShouldDeleteRoleById")
    void deleteRole() {
        //given
        Role role = new Role();
        role.setName("ADMIN");

        //when
        Mockito.when(roleRepository.findById(1L)).thenReturn(Optional.of(role));
        roleService.deleteRole(1L);

        //then
        Mockito.verify(roleRepository).findById(1L);
        Mockito.verify(roleRepository).deleteById(1L);
    }

    @Test
    @DisplayName("shouldReturnUpdateRoleIfIsExist")
    void updateRole1() {
        //given
        Role role = new Role();
        role.setName("Role");

        RoleDTO roleDto = new RoleDTO();
        roleDto.setName("Role");

        //when
        Mockito.when(roleConverter.dtoToEntity(roleDto)).thenReturn(role);
        Mockito.when(roleRepository.existsById(1L)).thenReturn(true);
        Mockito.when(roleRepository.findById(1L)).thenReturn(Optional.of(role));
        Mockito.when(roleRepository.save(role)).thenReturn(role);

        Role actualRole = roleService.updateRole(roleDto, 1L);

        //then
        Mockito.verify(roleRepository).save(role);
        Mockito.verify(roleRepository).findById(1L);
        Mockito.verify(roleConverter).dtoToEntity(roleDto);
        assertThat(actualRole).isEqualTo(role);
    }

    @Test
    @DisplayName("shouldReturnUpdateRoleIfIsNotExist")
    void updateRole2() {
        //given
        Role role = new Role();
        role.setName("Role");

        RoleDTO roleDto = new RoleDTO();
        roleDto.setName("Role");

        //when
        Mockito.when(roleConverter.dtoToEntity(roleDto)).thenReturn(role);
        Mockito.when(roleRepository.existsById(1L)).thenReturn(false);

        //then
        assertThatThrownBy(() -> roleService.updateRole(roleDto, 1L)).isExactlyInstanceOf(RoleException.class);

    }
}