package com.syncretis.SpringDataProject.converters;

import com.syncretis.SpringDataProject.dto.DepartmentDTO;
import com.syncretis.SpringDataProject.entities.Department;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class DepartmentConverterTest {

    private final DepartmentConverter departmentConverter = new DepartmentConverter();

    @Test
    @DisplayName("shouldConvertEntityToDto")
    void entityToDto() {
        //given
        Department department = new Department();
        department.setId(15L);
        department.setName("Department of test");

        //when
        DepartmentDTO actual  = departmentConverter.entityToDto(department);

        //then
        DepartmentDTO expected  = new DepartmentDTO();
        expected .setId(15L);
        expected .setName("Department of test");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("shouldConvertDtoToEntity")
    void dtoToEntity() {
        //given
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(15L);
        departmentDTO.setName("Department of test");

        //when
        Department actual = departmentConverter.dtoToEntity(departmentDTO);

        //then
        Department expected = new Department();
        expected.setId(15L);
        expected.setName("Department of test");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("shouldConvertEntityListToDtoList")
    void entityListToDtoList() {
        //given
        List<Department> departmentList = new ArrayList<>();

        Department department1 = new Department();
        department1.setId(15L);
        department1.setName("Department of test1");

        Department department2 = new Department();
        department2.setId(10L);
        department2.setName("Department of test2");

        departmentList.add(department1);
        departmentList.add(department2);
        //when
        List<DepartmentDTO> actual  = departmentConverter.entityToDto(departmentList);

        //then
        List<DepartmentDTO> expected = new ArrayList<>();

        DepartmentDTO departmentDTO1  = new DepartmentDTO();
        departmentDTO1.setId(15L);
        departmentDTO1.setName("Department of test1");

        DepartmentDTO departmentDTO2  = new DepartmentDTO();
        departmentDTO2.setId(10L);
        departmentDTO2.setName("Department of test2");

        expected.add(departmentDTO1);
        expected.add(departmentDTO2);

        for (int i = 0; i < expected.size(); i++) {
            assertThat(actual.get(i)).isEqualTo(expected.get(i));
        }

    }

    @Test
    @DisplayName("shouldConvertDtoListToEntityList")
    void dtoListToEntityList() {
        //given
        List<DepartmentDTO> departmentList = new ArrayList<>();

        DepartmentDTO departmentDTO1  = new DepartmentDTO();
        departmentDTO1.setId(15L);
        departmentDTO1.setName("Department of test1");

        DepartmentDTO departmentDTO2  = new DepartmentDTO();
        departmentDTO2.setId(10L);
        departmentDTO2.setName("Department of test2");

        departmentList.add(departmentDTO1);
        departmentList.add(departmentDTO2);

        //when
        List<Department> actual  = departmentConverter.dtoToEntity(departmentList);

        //then
        List<Department> expected = new ArrayList<>();

        Department department1 = new Department();
        department1.setId(15L);
        department1.setName("Department of test1");

        Department department2 = new Department();
        department2.setId(10L);
        department2.setName("Department of test2");

        expected.add(department1);
        expected.add(department2);

        for (int i = 0; i < expected.size(); i++) {
            assertThat(actual.get(i)).isEqualTo(expected.get(i));
        }
    }
}