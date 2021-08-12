package com.syncretis.SpringDataProject.services;

import com.syncretis.SpringDataProject.converters.DepartmentConverter;
import com.syncretis.SpringDataProject.dto.DepartmentDTO;
import com.syncretis.SpringDataProject.dto.PersonDTO;
import com.syncretis.SpringDataProject.exceptions.DepartmentNotFoundException;
import com.syncretis.SpringDataProject.entities.Department;
import com.syncretis.SpringDataProject.repositories.DepartmentRepository;
import com.syncretis.SpringDataProject.validator.DepartmentDTOValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentConverter departmentConverter;
    private final DepartmentDTOValidator departmentDTOValidator;


    public DepartmentService(DepartmentRepository departmentRepository, DepartmentConverter departmentConverter, DepartmentDTOValidator departmentDTOValidator) {
        this.departmentRepository = departmentRepository;
        this.departmentConverter = departmentConverter;
        this.departmentDTOValidator = departmentDTOValidator;
    }

    public List<DepartmentDTO> getDepartments() {
        List<Department> listDepartment = departmentRepository.findAll();
        List<DepartmentDTO> departmentDTOS = departmentConverter.entityToDto(listDepartment);
        return departmentDTOS;
    }

    public DepartmentDTO getDepartments(Long departmentId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new DepartmentNotFoundException(HttpStatus.NOT_FOUND));
        DepartmentDTO departmentDTO = departmentConverter.entityToDto(department);
        return departmentDTO;
    }

    public Department addNewDepartment(DepartmentDTO departmentDTO) {
        departmentDTOValidator.dataBinderValidation(departmentDTO);;
        Department department = departmentConverter.dtoToEntity(departmentDTO);
        return departmentRepository.save(department);
    }

    public void deleteDepartment(Long id) {
        departmentRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException(HttpStatus.NOT_FOUND));
        departmentRepository.deleteById(id);
    }

    public Department updateDepartment(DepartmentDTO newDepartment, Long id) {
        Department department;
        departmentDTOValidator.dataBinderValidation(newDepartment);
        Department departmentEntity = departmentConverter.dtoToEntity(newDepartment);
        if(departmentRepository.existsById(id)){
            department = departmentRepository.findById(id).orElse(null);
            department.setName(departmentEntity.getName());
        }else{
            throw new DepartmentNotFoundException(HttpStatus.NOT_FOUND);
        }
        return departmentRepository.save(department);
    }

    public Department checkAndReturnDepartment(PersonDTO personDTO) {
        //TODO: "Stavitskii Vladimir"  12.08.2021 =>  change findById to existsById in services update method and checkAndReturn method
        Department department = new Department();
        if (personDTO.getDepartment().getId() != null) {
            Optional<Department> optional = departmentRepository.findById(personDTO.getDepartment().getId());
            if (optional.isPresent()) {
                department = optional.get();
            }
        } else {
            throw new DepartmentNotFoundException(HttpStatus.NOT_FOUND);
        }
        return department;
    }
}
