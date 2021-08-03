package com.syncretis.SpringDataProject.services;

import com.syncretis.SpringDataProject.converters.DepartmentConverter;
import com.syncretis.SpringDataProject.dto.DepartmentDTO;
import com.syncretis.SpringDataProject.dto.PersonDTO;
import com.syncretis.SpringDataProject.exceptions.DepartmentException;
import com.syncretis.SpringDataProject.models.Department;
import com.syncretis.SpringDataProject.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository    departmentRepository;

    @Autowired
    private DepartmentConverter departmentConverter;

    public List<DepartmentDTO> getDepartments() {
        List<Department> listDepartment = departmentRepository.findAll();
        List<DepartmentDTO> departmentDTOS = departmentConverter.entityToDto(listDepartment);
        return departmentDTOS;
    }

    public DepartmentDTO getDepartments(Long departmentId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new DepartmentException(HttpStatus.NOT_FOUND));
        DepartmentDTO departmentDTO = departmentConverter.entityToDto(department);
        return departmentDTO;
    }

    public void addNewDepartment(DepartmentDTO departmentDTO) {
        Department department = departmentConverter.dtoToEntity(departmentDTO);
        departmentRepository.save(department);
    }

    public void deleteDepartment(Long id) {
        boolean exists = departmentRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Department with id = " + id + " does not exists");
        }
        departmentRepository.deleteById(id);
    }

    public Department updateDepartment(DepartmentDTO newDepartment, Long id) {
        Department departmentEntity = departmentConverter.dtoToEntity(newDepartment);
        return departmentRepository.findById(id)
                .map(department -> {
                    department.setName(departmentEntity.getName());
                    return departmentRepository.save(department);
                })
                .orElseThrow(() -> new DepartmentException(HttpStatus.BAD_REQUEST));
    }

    public Department checkAndReturnDepartment(PersonDTO personDTO){
        Department department;
        if(personDTO.getDepartment().getId() != null) {
            Optional<Department> optional = departmentRepository.findById(personDTO.getDepartment().getId());
            System.out.println("нашел optional departmentById" + optional);
            if(optional.isPresent()) {
                department = optional.get();
            } else {
                department = new Department();
            }
        } else {
            throw new DepartmentException(HttpStatus.FORBIDDEN);
        }
        return department;
    }


}
