package com.syncretis.SpringDataProject.services;

import com.syncretis.SpringDataProject.converters.DepartmentConverter;
import com.syncretis.SpringDataProject.dto.DepartmentDTO;
import com.syncretis.SpringDataProject.dto.PersonDTO;
import com.syncretis.SpringDataProject.exceptions.DepartmentBadRequestException;
import com.syncretis.SpringDataProject.exceptions.DepartmentNotFoundException;
import com.syncretis.SpringDataProject.entities.Department;
import com.syncretis.SpringDataProject.repositories.DepartmentRepository;
import com.syncretis.SpringDataProject.validator.DepartmentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class DepartmentService {
    private final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

    private final DepartmentRepository departmentRepository;
    private final DepartmentConverter departmentConverter;
    private final DepartmentValidator departmentValidator;

    public DepartmentService(DepartmentRepository departmentRepository, DepartmentConverter departmentConverter, DepartmentValidator departmentValidator) {
        this.departmentRepository = departmentRepository;
        this.departmentConverter = departmentConverter;
        this.departmentValidator = departmentValidator;
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
        validate(departmentDTO);
        Department department = departmentConverter.dtoToEntity(departmentDTO);
        return departmentRepository.save(department);
    }

    public void deleteDepartment(Long id) {
        departmentRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException(HttpStatus.NOT_FOUND));
        departmentRepository.deleteById(id);
    }

    public Department updateDepartment(DepartmentDTO newDepartment, Long id) {
        validate(newDepartment);
        Department departmentEntity = departmentConverter.dtoToEntity(newDepartment);
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        Department department = optionalDepartment.orElseThrow(() -> new DepartmentNotFoundException(HttpStatus.NOT_FOUND));
        department.setName(departmentEntity.getName());
        return departmentRepository.save(department);
    }

    public Department checkAndReturnDepartment(PersonDTO personDTO) {
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

    public void validate(DepartmentDTO departmentDTO) {
        final DataBinder dataBinder = new DataBinder(departmentDTO);
        dataBinder.addValidators(departmentValidator);
        dataBinder.validate(departmentDTO);

        if (dataBinder.getBindingResult().hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            for (ObjectError allError : dataBinder.getBindingResult().getAllErrors()) {
                errorMessage.append(messageSource.getMessage(allError, Locale.getDefault())).append('\n');
            }
            throw new DepartmentBadRequestException(errorMessage.toString());
        }
    }
}
