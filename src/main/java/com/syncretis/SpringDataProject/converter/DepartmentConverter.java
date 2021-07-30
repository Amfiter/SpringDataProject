package com.syncretis.SpringDataProject.converter;

import com.syncretis.SpringDataProject.dto.DepartmentDTO;
import com.syncretis.SpringDataProject.models.Department;

public class DepartmentConverter {

    public DepartmentDTO entityToDto(Department department){
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(departmentDTO.getId());
        departmentDTO.setName(departmentDTO.getName());
        return departmentDTO;
    }

    public Department dtoToEntity(DepartmentDTO departmentDTO){
        Department department = new Department();
        department.setId(department.getId());
        department.setName(departmentDTO.getName());
        return department;
    }
}
