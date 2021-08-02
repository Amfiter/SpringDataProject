package com.syncretis.SpringDataProject.converters;

import com.syncretis.SpringDataProject.dto.DepartmentDTO;
import com.syncretis.SpringDataProject.models.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DepartmentConverter {

    public DepartmentDTO entityToDto(Department department) {

        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(department.getId());
        departmentDTO.setName(department.getName());
        return departmentDTO;
    }

    public List<DepartmentDTO> entityToDto(List<Department> department) {
        return department.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public Department dtoToEntity(DepartmentDTO departmentDTO) {
        Department department = new Department();
        department.setId(departmentDTO.getId());
        department.setName(departmentDTO.getName());
        return department;
    }

    public List<Department> dtoToEntity(List<DepartmentDTO> departmentDTO) {
        return departmentDTO.stream().map(DTO -> dtoToEntity(DTO)).collect(Collectors.toList());
    }
}
